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


public final class SQLUpdateColumnCommandTest
{
    public SQLUpdateColumnCommandTest()
    {
        super();
    }

    private static DataBaseConnectionPool dataBaseConnectionPool = null;

    @BeforeClass
    public final void createDataBaseConnectionPool()
            throws DataBaseConnectionPoolCreatingException
    {
        SQLUpdateColumnCommandTest.dataBaseConnectionPool
                = new DataBaseConnectionPool(SQLUpdateColumnCommandTest.URL_OF_DATA_BASE,
                SQLUpdateColumnCommandTest.USER_OF_DATA_BASE,
                SQLUpdateColumnCommandTest.PASSWORD_OF_DATA_BASE);
    }

    @AfterClass
    public final void closeDataBaseConnectionPool()
            throws IOException
    {
        SQLUpdateColumnCommandTest.dataBaseConnectionPool.close();
    }

    private static final String URL_OF_DATA_BASE = "jdbc:postgresql://localhost:5432/employee_data_base";
    private static final String USER_OF_DATA_BASE = "postgres";
    private static final String PASSWORD_OF_DATA_BASE = "kakawka228";

    @Test
    public final void commandShouldBeCreated()
    {
        final String nameOfTable = "employee_for_testing";
        final String nameOfUpdatedColumn = "salary";
        final String newValueOfColumn = "salary * 1.1";
        final SQLUpdateColumnCommand sqlUpdateColumnCommand
                = new SQLUpdateColumnCommand(nameOfTable, nameOfUpdatedColumn, newValueOfColumn);
        final String expectedDescriptionOfCommand = "UPDATE " + nameOfTable + " SET "
                + nameOfUpdatedColumn + " = " + newValueOfColumn;
        final String actualDescriptionOfCommand = sqlUpdateColumnCommand.getDescriptionOfCommand();
        Assert.assertEquals(actualDescriptionOfCommand, expectedDescriptionOfCommand);
    }

    @Test
    public final void commandShouldBeCreatedWithWhereClause()
    {
        final String nameOfTable = "employee_for_testing";
        final String nameOfUpdatedColumn = "salary";
        final String newValueOfColumn = "salary * 1.1";
        final String conditionOfUpdating
                = "salary < (SELECT AVG(salary) FROM employee_for_testing)";
        final SQLUpdateColumnCommand sqlUpdateColumnCommand
                = new SQLUpdateColumnCommand(nameOfTable, nameOfUpdatedColumn,
                newValueOfColumn, conditionOfUpdating);
        final String expectedDescriptionOfCommand = "UPDATE " + nameOfTable + " SET "
                + nameOfUpdatedColumn + " = " + newValueOfColumn + " WHERE " + conditionOfUpdating;
        final String actualDescriptionOfCommand = sqlUpdateColumnCommand.getDescriptionOfCommand();
        Assert.assertEquals(actualDescriptionOfCommand, expectedDescriptionOfCommand);
    }

    @Test
    public final void commandShouldBeExecuted()
            throws DataBaseConnectionPoolAccessConnectionException
    {
        final String nameOfTable = "employee_for_testing";
        final String nameOfUpdatedColumn = "salary";
        final String newValueOfColumn = "salary * 1.1";
        final String conditionOfUpdating
                = "salary::numeric < (SELECT AVG(salary::numeric) FROM employee_for_testing)";
        final SQLUpdateColumnCommand sqlUpdateColumnCommand
                = new SQLUpdateColumnCommand(nameOfTable, nameOfUpdatedColumn,
                newValueOfColumn, conditionOfUpdating);
        final Connection connectionToDataBase = SQLUpdateColumnCommandTest
                .dataBaseConnectionPool.findAvailableConnection();
        boolean exceptionIsArisen = false;
        try
        {
            sqlUpdateColumnCommand.execute(connectionToDataBase);
        }
        catch(final SQLCommandExecutionException notExpectedException)
        {
            exceptionIsArisen = true;
        }
        finally
        {
            SQLUpdateColumnCommandTest.dataBaseConnectionPool.returnConnectionToThePool(connectionToDataBase);
        }
        Assert.assertFalse(exceptionIsArisen);
    }

    @Test(expectedExceptions = SQLCommandExecutionException.class)
    public final void commandShouldNotBeExecuted()
            throws DataBaseConnectionPoolAccessConnectionException,
                   SQLCommandExecutionException
    {
        final String nameOfTable = "not_existing_table";
        final String nameOfUpdatedColumn = "salary";
        final String newValueOfColumn = "salary * 1.1";
        final String conditionOfUpdating
                = "salary < (SELECT AVG(salary) FROM employee_for_testing)";
        final SQLUpdateColumnCommand sqlUpdateColumnCommand
                = new SQLUpdateColumnCommand(nameOfTable, nameOfUpdatedColumn,
                newValueOfColumn, conditionOfUpdating);
        final Connection connectionToDataBase = SQLUpdateColumnCommandTest
                .dataBaseConnectionPool.findAvailableConnection();
        try
        {
            sqlUpdateColumnCommand.execute(connectionToDataBase);
        }
        finally
        {
            SQLUpdateColumnCommandTest.dataBaseConnectionPool.returnConnectionToThePool(connectionToDataBase);
        }
    }
}
