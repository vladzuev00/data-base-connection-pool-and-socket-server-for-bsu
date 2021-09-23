package by.bsu.zuevvlad.seventhlab.logic.databaseconnectionpool;

import by.bsu.zuevvlad.seventhlab.logic.databaseconnectionpool.exception.DataBaseConnectionPoolAccessConnectionException;
import by.bsu.zuevvlad.seventhlab.logic.databaseconnectionpool.exception.DataBaseConnectionPoolCreatingException;
import by.bsu.zuevvlad.seventhlab.logic.databaseconnectionpool.exception.DataBaseConnectionPoolFullingException;
import by.bsu.zuevvlad.seventhlab.logic.databaseconnectionpool.validator.DataBaseConnectionPoolValidator;
import javafx.util.Pair;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.*;

public final class DataBaseConnectionPool implements AutoCloseable
{
    private final Future<BlockingQueue<Connection>> holderOfAvailableConnections;

    public DataBaseConnectionPool(final String URLOfDataBase, final String user,
                                  final String password)
            throws DataBaseConnectionPoolCreatingException
    {
        this(URLOfDataBase, user, password, DataBaseConnectionPool.DEFAULT_AMOUNT_OF_INVOLVED_CONNECTIONS);
    }

    public static final int DEFAULT_AMOUNT_OF_INVOLVED_CONNECTIONS = 10;

    public DataBaseConnectionPool(final String URLOfDataBase, final String user,
                                  final String password, final int amountOfInvolvedConnections)
            throws DataBaseConnectionPoolCreatingException
    {
        if(!DataBaseConnectionPool.DATA_BASE_CONNECTION_POOL_VALIDATOR
                .isValidAmountOfInvolvedConnections(amountOfInvolvedConnections))
        {
            throw new DataBaseConnectionPoolCreatingException("Impossible to create pool of connections to data base by "
                    + "given not valid amount of involved connections: " + amountOfInvolvedConnections + ".");
        }
        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        final PoolFuller poolFuller = new PoolFuller(URLOfDataBase, user, password, amountOfInvolvedConnections);
        this.holderOfAvailableConnections = executorService.submit(poolFuller);
    }

    private static final DataBaseConnectionPoolValidator DATA_BASE_CONNECTION_POOL_VALIDATOR
            = new DataBaseConnectionPoolValidator();

    private static final class PoolFuller implements Callable<BlockingQueue<Connection>>
    {
        private final String URLOfDatabase;
        private final String user;
        private final String password;
        private final int amountOfFulledConnections;

        public PoolFuller(final String URLOfDataBase, final String user,
                          final String password, final int amountOfFulledConnections)
        {
            this.URLOfDatabase = URLOfDataBase;
            this.user = user;
            this.password = password;
            this.amountOfFulledConnections = amountOfFulledConnections;
        }

        @Override
        public final BlockingQueue<Connection> call()
        {
            try
            {
                final BlockingQueue<Connection> connections = new ArrayBlockingQueue<Connection>(
                        this.amountOfFulledConnections);
                Connection currentFulledConnection;
                for(int i = 0; i < this.amountOfFulledConnections; i++)
                {
                    currentFulledConnection = DriverManager.getConnection(this.URLOfDatabase, this.user, this.password);
                    connections.add(currentFulledConnection);
                }
                return connections;
            }
            catch(final SQLException cause)
            {
                throw new DataBaseConnectionPoolFullingException(cause);
            }
        }
    }

    public final int findAmountOfAvailableConnections()
    {
        try
        {
            return this.holderOfAvailableConnections.get().size();
        }
        catch(final ExecutionException | InterruptedException cause)
        {
            throw new DataBaseConnectionPoolFullingException(cause);
        }
    }

    public final Connection findAvailableConnection()
            throws DataBaseConnectionPoolAccessConnectionException
    {
       try
       {
           final BlockingQueue<Connection> availableConnections = this.holderOfAvailableConnections.get();
           final Connection foundConnection = availableConnections.poll(
                   DataBaseConnectionPool.PROPERTIES_OF_MAXIMUM_TIME_OF_WAITING_CONNECTION.getKey(),
                   DataBaseConnectionPool.PROPERTIES_OF_MAXIMUM_TIME_OF_WAITING_CONNECTION.getValue());
           if(foundConnection == null)
           {
               throw new DataBaseConnectionPoolAccessConnectionException("Trying of getting connection from connection"
                       + " pool is very long.");
           }
           return foundConnection;
       }
       catch(final ExecutionException cause)
       {
           throw new DataBaseConnectionPoolFullingException(cause);
       }
       catch(final InterruptedException cause)
       {
           throw new DataBaseConnectionPoolAccessConnectionException(cause);
       }
    }

    private static final Pair<Long, TimeUnit> PROPERTIES_OF_MAXIMUM_TIME_OF_WAITING_CONNECTION
            = new Pair<Long, TimeUnit>(5L, TimeUnit.SECONDS);

    public final void returnConnectionToThePool(final Connection returnedConnection)
    {
        try
        {
            final BlockingQueue<Connection> availableConnections = this.holderOfAvailableConnections.get();
            availableConnections.add(returnedConnection);
        }
        catch(final InterruptedException | ExecutionException cause)
        {
            throw new DataBaseConnectionPoolFullingException(cause);
        }
    }

    @Override
    public final void close() throws IOException
    {
        try
        {
            final BlockingQueue<Connection> availableConnections = this.holderOfAvailableConnections.get();
            for(final Connection closedConnection : availableConnections)
            {
                closedConnection.close();
            }
        }
        catch(final ExecutionException | InterruptedException cause)
        {
            throw new DataBaseConnectionPoolFullingException(cause);
        }
        catch(final SQLException cause)
        {
            throw new IOException(cause);
        }
    }
}
