package by.bsu.zuevvlad.seventhlab.logic.command.sql.update;

import by.bsu.zuevvlad.seventhlab.logic.command.sql.exception.SQLCommandExecutionException;
import by.bsu.zuevvlad.seventhlab.logic.databaseconnectionpool.DataBaseConnectionPool;
import by.bsu.zuevvlad.seventhlab.logic.databaseconnectionpool.exception.DataBaseConnectionPoolAccessConnectionException;
import by.bsu.zuevvlad.seventhlab.logic.databaseconnectionpool.exception.DataBaseConnectionPoolCreatingException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.Connection;

public final class SQLDeleteRowCommandTest
{
    public SQLDeleteRowCommandTest()
    {
        super();
    }

    private static DataBaseConnectionPool dataBaseConnectionPool = null;

    @BeforeClass
    public final void createDataBaseConnectionPool()
            throws DataBaseConnectionPoolCreatingException
    {
        SQLDeleteRowCommandTest.dataBaseConnectionPool
                = new DataBaseConnectionPool(SQLDeleteRowCommandTest.URL_OF_DATA_BASE,
                SQLDeleteRowCommandTest.USER_OF_DATA_BASE,
                SQLDeleteRowCommandTest.PASSWORD_OF_DATA_BASE);
    }

    @AfterClass
    public final void closeDataBaseConnectionPool()
            throws IOException
    {
        SQLDeleteRowCommandTest.dataBaseConnectionPool.close();
    }

    private static final String URL_OF_DATA_BASE = "jdbc:postgresql://localhost:5432/employee_data_base";
    private static final String USER_OF_DATA_BASE = "postgres";
    private static final String PASSWORD_OF_DATA_BASE = "kakawka228";

    @Test
    public final void commandShouldBeCreated()
    {
        final String nameOfTable = "employee_for_testing";
        final SQLDeleteRowCommand sqlDeleteRowCommand = new SQLDeleteRowCommand(nameOfTable);
        final String expectedDescriptionOfCommand = "DELETE FROM " + nameOfTable;
        final String actualDescriptionOfCommand = sqlDeleteRowCommand.getDescriptionOfCommand();
        Assert.assertEquals(actualDescriptionOfCommand, expectedDescriptionOfCommand);
    }

    @Test
    public final void commandShouldBeCreatedWithWhereClause()
    {
        final String nameOfTable = "employee_for_testing";
        final String conditionOfDeleting = "employee_id = (SELECT employee_id FROM employee" +
                " WHERE (first_name, last_name, patronymic) = ('Vlad', 'Zuev', 'Sergeevich'))";
        final SQLDeleteRowCommand sqlDeleteRowCommand = new SQLDeleteRowCommand(
                nameOfTable, conditionOfDeleting);
        final String expectedDescriptionOfCommand = "DELETE FROM " + nameOfTable + " WHERE " + conditionOfDeleting;
        final String actualDescriptionOfCommand = sqlDeleteRowCommand.getDescriptionOfCommand();
        Assert.assertEquals(actualDescriptionOfCommand, expectedDescriptionOfCommand);
    }

    @Test
    public final void commandShouldBeExecuted()
            throws DataBaseConnectionPoolAccessConnectionException
    {
        final String nameOfTable = "employee_for_testing";
        final String conditionOfDeleting = "employee_id = (SELECT employee_id FROM employee_for_testing" +
                " WHERE (first_name, last_name, patronymic) = ('Vlad', 'Zuev', 'Sergeevich'))";
        final SQLDeleteRowCommand sqlDeleteRowCommand = new SQLDeleteRowCommand(
                nameOfTable, conditionOfDeleting);
        final Connection connectionToDataBase = SQLDeleteRowCommandTest.
                dataBaseConnectionPool.findAvailableConnection();
        boolean exceptionIsArisen = false;
        try
        {
            sqlDeleteRowCommand.execute(connectionToDataBase);
        }
        catch(final SQLCommandExecutionException notExpectedException)
        {
            exceptionIsArisen = true;
        }
        finally
        {
            SQLDeleteRowCommandTest.dataBaseConnectionPool.returnConnectionToThePool(connectionToDataBase);
        }
        Assert.assertFalse(exceptionIsArisen);
    }

    @Test(expectedExceptions = SQLCommandExecutionException.class)
    public final void commandShouldNotBeExecuted()
            throws DataBaseConnectionPoolAccessConnectionException,
                   SQLCommandExecutionException
    {
        final String nameOfTable = "not_existing_table";
        final String conditionOfDeleting = "employee_id = (SELECT employee_id FROM employee" +
                " WHERE (first_name, last_name, patronymic) = ('Vlad', 'Zuev', 'Sergeevich'))";
        final SQLDeleteRowCommand sqlDeleteRowCommand = new SQLDeleteRowCommand(
                nameOfTable, conditionOfDeleting);
        final Connection connectionToDataBase = SQLDeleteRowCommandTest.
                dataBaseConnectionPool.findAvailableConnection();
        try
        {
            sqlDeleteRowCommand.execute(connectionToDataBase);
        }
        finally
        {
            SQLDeleteRowCommandTest.dataBaseConnectionPool.returnConnectionToThePool(connectionToDataBase);
        }
    }
}
