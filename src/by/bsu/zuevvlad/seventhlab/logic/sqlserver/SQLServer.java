package by.bsu.zuevvlad.seventhlab.logic.sqlserver;

import by.bsu.zuevvlad.seventhlab.logic.databaseconnectionpool.DataBaseConnectionPool;
import by.bsu.zuevvlad.seventhlab.logic.databaseconnectionpool.exception.DataBaseConnectionPoolAccessConnectionException;
import by.bsu.zuevvlad.seventhlab.logic.databaseconnectionpool.exception.DataBaseConnectionPoolCreatingException;
import by.bsu.zuevvlad.seventhlab.logic.sqlcommandalalyzer.SQLCommandAnalyzer;
import by.bsu.zuevvlad.seventhlab.logic.sqlserver.exception.CommunicatorRunningException;
import by.bsu.zuevvlad.seventhlab.logic.sqlserver.exception.SQLServerCreatingException;
import by.bsu.zuevvlad.seventhlab.logic.sqlserver.exception.SQLServerRunningException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class SQLServer implements Runnable, AutoCloseable
{
    private ServerSocket serverSocket;
    private final DataBaseConnectionPool dataBaseConnectionPool;

    public SQLServer(final int listenedPort, final String URLOfDataBase, final String user, final String password)
            throws SQLServerCreatingException
    {
        super();
        try
        {
            this.serverSocket = new ServerSocket(listenedPort);
            this.dataBaseConnectionPool = new DataBaseConnectionPool(URLOfDataBase, user, password);
        }
        catch(final IOException cause)
        {
            throw new SQLServerCreatingException(cause);
        }
        catch(final DataBaseConnectionPoolCreatingException cause)
        {
            final SQLServerCreatingException mainException = new SQLServerCreatingException(cause);
            try
            {
                this.serverSocket.close();
            }
            catch(final IOException suppressedException)
            {
                mainException.addSuppressed(suppressedException);
            }
            throw mainException;
        }
    }

    @Override
    public final void run()
    {
        final Thread currentThread = Thread.currentThread();
        Socket incomingSocket = null;
        Connection currentGivenConnection;
        Communicator currentStartedCommunicator;
        final ExecutorService executorService = Executors.newCachedThreadPool();
        try
        {
            while(!currentThread.isInterrupted())
            {
                incomingSocket = this.serverSocket.accept();
                currentGivenConnection = this.dataBaseConnectionPool.findAvailableConnection();
                currentStartedCommunicator = new Communicator(incomingSocket, currentGivenConnection);
                executorService.execute(currentStartedCommunicator);
            }
        }
        catch(final IOException cause)
        {
            throw new SQLServerRunningException(cause);
        }
        catch(final DataBaseConnectionPoolAccessConnectionException cause)
        {
            final SQLServerRunningException mainException = new SQLServerRunningException(cause);
            try
            {
                incomingSocket.close();
            }
            catch(final IOException suppressedException)
            {
                mainException.addSuppressed(suppressedException);
            }
            throw mainException;
        }
    }

    private final class Communicator implements Runnable
    {
        private final Socket socket;
        private final Connection connection;
        private final SQLCommandAnalyzer sqlCommandAnalyzer;
        private final SQLCommandExecutor sqlCommandExecutor;

        public Communicator(final Socket socket, final Connection connection)
        {
            super();
            this.socket = socket;
            this.connection = connection;
            this.sqlCommandAnalyzer = new SQLCommandAnalyzer();
            this.sqlCommandExecutor = new SQLCommandExecutor();
        }

        @Override
        public final void run()
        {
            CommunicatorRunningException mainException = null;
            try
            {
                final InputStream inputStream = this.socket.getInputStream();
                final OutputStream outputStream = this.socket.getOutputStream();

                final Scanner scanner = new Scanner(inputStream);
                final boolean autoFlush = true;
                final PrintWriter printWriter = new PrintWriter(outputStream, autoFlush);
                printWriter.println(Communicator.MESSAGE_OF_GREETING);

                boolean done = false;
                String currentRequest;
                String currentResponse = null;
                while(!done && scanner.hasNextLine())
                {
                    currentRequest = scanner.nextLine();
                    if(this.sqlCommandAnalyzer.isUpdateCommand(currentRequest))
                    {
                        this.sqlCommandExecutor.executeUpdateCommand(currentRequest, this.connection);
                        currentResponse = Communicator.RESPONSE_AFTER_UPDATE_COMMAND;
                    }
                    else if(this.sqlCommandAnalyzer.isQueryCommand(currentRequest))
                    {
                        try(final ResultSet resultSet = this.sqlCommandExecutor.executeQueryCommand(
                                currentRequest, this.connection))
                        {
                            currentResponse = this.findDescriptionOfResultSet(resultSet);
                        }
                    }
                    else if(currentRequest.equals(Communicator.MESSAGE_TO_CLOSE_SOCKET_CONNECTION))
                    {
                        done = true;
                    }
                    else
                    {
                        currentResponse = Communicator.RESPONSE_ON_UNKNOWN_COMMAND;
                    }
                    if(!done)
                    {
                        printWriter.println(currentResponse);
                    }
                }
            }
            catch(final SQLException | IOException cause)
            {
                mainException = new CommunicatorRunningException(cause);
                throw mainException;
            }
            finally
            {
                SQLServer.this.dataBaseConnectionPool.returnConnectionToThePool(this.connection);
                try
                {
                    this.socket.close();
                }
                catch(final IOException exception)
                {
                    if(mainException != null)
                    {
                        mainException.addSuppressed(exception);
                    }
                    else
                    {
                        mainException = new CommunicatorRunningException(exception);
                    }
                    throw mainException;
                }
            }
        }

        private static final String MESSAGE_TO_CLOSE_SOCKET_CONNECTION = "CLOSE";
        private static final String MESSAGE_OF_GREETING = "Input SQL request to database "
                + "or '" + Communicator.MESSAGE_TO_CLOSE_SOCKET_CONNECTION + "' "
                + "to close socket connection.";
        private static final String RESPONSE_AFTER_UPDATE_COMMAND = "READY";
        private static final String RESPONSE_ON_UNKNOWN_COMMAND = "UNKNOWN COMMAND";

        private String findDescriptionOfResultSet(final ResultSet resultSet)
                throws SQLException
        {
            final StringBuilder builderOfDescription = new StringBuilder();
            final int amountOfColumns = resultSet.getMetaData().getColumnCount();
            final String delimiterOfProperties = " ";
            while(resultSet.next())
            {
                int i;
                for(i = 1; i < amountOfColumns; i++)
                {
                    builderOfDescription.append(resultSet.getString(i));
                    builderOfDescription.append(delimiterOfProperties);
                }
                builderOfDescription.append(resultSet.getString(i));
                builderOfDescription.append("\n\r");
            }
            return builderOfDescription.toString();
        }
    }

    private static final class SQLCommandExecutor
    {
        public SQLCommandExecutor()
        {
            super();
        }

        public final void executeUpdateCommand(final String descriptionOfCommand, final Connection connection)
                throws SQLException
        {
            try(final Statement statement = connection.createStatement())
            {
                statement.executeUpdate(descriptionOfCommand);
            }
        }

        public final ResultSet executeQueryCommand(final String descriptionOfCommand, final Connection connection)
                throws SQLException
        {
            final Statement statement = connection.createStatement();
            return statement.executeQuery(descriptionOfCommand);
        }
    }

    @Override
    public final void close()
            throws IOException
    {
        this.serverSocket.close();
        this.dataBaseConnectionPool.close();
    }
}
