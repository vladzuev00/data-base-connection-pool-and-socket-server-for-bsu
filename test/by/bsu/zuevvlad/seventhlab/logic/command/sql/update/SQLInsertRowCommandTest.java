package by.bsu.zuevvlad.seventhlab.logic.command.sql.update;

import by.bsu.zuevvlad.seventhlab.logic.command.sql.exception.SQLCommandExecutionException;
import by.bsu.zuevvlad.seventhlab.logic.command.sql.update.exception.SQLUpdateCommandExecutionException;
import by.bsu.zuevvlad.seventhlab.logic.databaseconnectionpool.DataBaseConnectionPool;
import by.bsu.zuevvlad.seventhlab.logic.databaseconnectionpool.exception.DataBaseConnectionPoolAccessConnectionException;
import by.bsu.zuevvlad.seventhlab.logic.databaseconnectionpool.exception.DataBaseConnectionPoolCreatingException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.Connection;

public final class SQLInsertRowCommandTest
{
    public SQLInsertRowCommandTest()
    {
        super();
    }

    private static DataBaseConnectionPool dataBaseConnectionPool = null;

    @BeforeClass
    public final void createDataBaseConnectionPool()
            throws DataBaseConnectionPoolCreatingException
    {
        SQLInsertRowCommandTest.dataBaseConnectionPool
                = new DataBaseConnectionPool(SQLInsertRowCommandTest.URL_OF_DATA_BASE,
                SQLInsertRowCommandTest.USER_OF_DATA_BASE,
                SQLInsertRowCommandTest.PASSWORD_OF_DATA_BASE);
    }

    @AfterClass
    public final void closeDataBaseConnectionPool()
            throws IOException
    {
        SQLInsertRowCommandTest.dataBaseConnectionPool.close();
    }

    private static final String URL_OF_DATA_BASE = "jdbc:postgresql://localhost:5432/employee_data_base";
    private static final String USER_OF_DATA_BASE = "postgres";
    private static final String PASSWORD_OF_DATA_BASE = "kakawka228";

    @Test
    public final void commandShouldBeCreated()
    {
        final String nameOfTable = "employee_for_testing";
        final String namesOfColumns = "first_name, last_name, patronymic, salary";
        final String valuesOfColumns = "Vlad, Zuev, Sergeevich, 20000";
        final SQLInsertRowCommand sqlInsertRowCommand
                = new SQLInsertRowCommand(nameOfTable, namesOfColumns, valuesOfColumns);
        final String expectedDescriptionOfCommand = "INSERT INTO " + nameOfTable + " (" + namesOfColumns + ") "
                + "VALUES (" + valuesOfColumns + ")";
        final String actualDescriptionOfCommand = sqlInsertRowCommand.getDescriptionOfCommand();
        Assert.assertEquals(actualDescriptionOfCommand, expectedDescriptionOfCommand);
    }

    @Test
    public final void commandShouldBeExecuted()
            throws DataBaseConnectionPoolAccessConnectionException,
                   SQLCommandExecutionException
    {
        final String nameOfTable = "employee_for_testing";
        final String namesOfColumns = "first_name, last_name, patronymic, salary";
        final String valuesOfColumns = "'Vlad', 'Zuev', 'Sergeevich', 20000";
        final SQLInsertRowCommand sqlInsertRowCommand
                = new SQLInsertRowCommand(nameOfTable, namesOfColumns, valuesOfColumns);
        final Connection connectionToDataBase = SQLInsertRowCommandTest.dataBaseConnectionPool
                .findAvailableConnection();
        boolean exceptionIsArisen = false;
        try
        {
            sqlInsertRowCommand.execute(connectionToDataBase);
        }
        catch(final SQLUpdateCommandExecutionException notExpectedException)
        {
            exceptionIsArisen = true;
            System.out.println(notExpectedException);
        }
        finally
        {
            SQLInsertRowCommandTest.dataBaseConnectionPool.returnConnectionToThePool(connectionToDataBase);
        }
        Assert.assertFalse(exceptionIsArisen);
    }

    @Test(expectedExceptions = SQLCommandExecutionException.class)
    public final void commandShouldNotBeExecuted()
            throws DataBaseConnectionPoolAccessConnectionException,
                   SQLCommandExecutionException
    {
        final String nameOfTable = "not_existing_table";
        final String namesOfColumns = "first_name, last_name, patronymic, salary";
        final String valuesOfColumns = "'Vlad', 'Zuev', 'Sergeevich', 20000";
        final SQLInsertRowCommand sqlInsertRowCommand
                = new SQLInsertRowCommand(nameOfTable, namesOfColumns, valuesOfColumns);
        final Connection connectionToDataBase = SQLInsertRowCommandTest.dataBaseConnectionPool
                .findAvailableConnection();
        try
        {
            sqlInsertRowCommand.execute(connectionToDataBase);
        }
        finally
        {
            SQLInsertRowCommandTest.dataBaseConnectionPool.returnConnectionToThePool(connectionToDataBase);
        }
    }
}
