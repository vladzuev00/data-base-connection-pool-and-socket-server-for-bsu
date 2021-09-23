package by.bsu.zuevvlad.seventhlab.logic.command.sql.query;

import by.bsu.zuevvlad.seventhlab.logic.command.sql.query.exception.SQLQueryCommandExecutionException;
import by.bsu.zuevvlad.seventhlab.logic.databaseconnectionpool.DataBaseConnectionPool;
import by.bsu.zuevvlad.seventhlab.logic.databaseconnectionpool.exception.DataBaseConnectionPoolAccessConnectionException;
import by.bsu.zuevvlad.seventhlab.logic.databaseconnectionpool.exception.DataBaseConnectionPoolCreatingException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public final class SQLSelectCommandTest
{
    public SQLSelectCommandTest()
    {
        super();
    }

    private static DataBaseConnectionPool dataBaseConnectionPool = null;

    @BeforeClass
    public final void createDataBaseConnectionPool()
            throws DataBaseConnectionPoolCreatingException
    {
        SQLSelectCommandTest.dataBaseConnectionPool
                = new DataBaseConnectionPool(SQLSelectCommandTest.URL_OF_DATA_BASE,
                SQLSelectCommandTest.USER_OF_DATA_BASE,
                SQLSelectCommandTest.PASSWORD_OF_DATA_BASE);
    }

    @AfterClass
    public final void closeDataBaseConnectionPool()
            throws IOException
    {
        SQLSelectCommandTest.dataBaseConnectionPool.close();
    }

    private static final String URL_OF_DATA_BASE = "jdbc:postgresql://localhost:5432/employee_data_base";
    private static final String USER_OF_DATA_BASE = "postgres";
    private static final String PASSWORD_OF_DATA_BASE = "kakawka228";

    @Test
    public final void commandShouldBeCreated()
    {
        final String namesOfSelectedColumns = "employee_id, first_name, last_name, patronymic, salary";
        final String nameOfTable = "employee_for_testing";
        final SQLSelectCommand sqlSelectCommand = new SQLSelectCommand(namesOfSelectedColumns, nameOfTable);
        final String expectedDescriptionOfCommand = "SELECT " + namesOfSelectedColumns + " FROM " + nameOfTable;
        final String actualDescriptionOfCommand = sqlSelectCommand.getDescriptionOfCommand();
        Assert.assertEquals(actualDescriptionOfCommand, expectedDescriptionOfCommand);
    }

    @Test
    public final void commandShouldBeCreatedWithWhereClause()
    {
        final String namesOfSelectedColumns = "employee_id, first_name, last_name, patronymic, salary";
        final String nameOfTable = "employee_for_testing";
        final String conditionOfSelection = "salary::numeric > (SELECT AVG(salary::numeric) FROM employee_for_testing)";
        final SQLSelectCommand sqlSelectCommand = new SQLSelectCommand(namesOfSelectedColumns,
                nameOfTable, conditionOfSelection);
        final String expectedDescriptionOfCommand = "SELECT " + namesOfSelectedColumns + " FROM " + nameOfTable
                + " WHERE " + conditionOfSelection;
        final String actualDescriptionOfCommand = sqlSelectCommand.getDescriptionOfCommand();
        Assert.assertEquals(actualDescriptionOfCommand, expectedDescriptionOfCommand);
    }

    @Test
    public final void commandShouldBeExecuted()
            throws DataBaseConnectionPoolAccessConnectionException,
                   SQLException
    {
        final String namesOfSelectedColumns = "employee_id, first_name, last_name, patronymic, salary";
        final String nameOfTable = "employee_for_testing";
        final String conditionOfSelection = "salary::numeric > (SELECT AVG(salary::numeric) FROM employee_for_testing)";
        final SQLSelectCommand sqlSelectCommand = new SQLSelectCommand(namesOfSelectedColumns,
                nameOfTable, conditionOfSelection);
        final Connection connectionToDataBase = SQLSelectCommandTest.dataBaseConnectionPool.findAvailableConnection();
        boolean exceptionIsArisen = false;
        try
        {
            sqlSelectCommand.execute(connectionToDataBase).close();
        }
        catch(final SQLQueryCommandExecutionException notExpectedException)
        {
            exceptionIsArisen = true;
        }
        finally
        {
            SQLSelectCommandTest.dataBaseConnectionPool.returnConnectionToThePool(connectionToDataBase);
        }
        Assert.assertFalse(exceptionIsArisen);
    }

    @Test(expectedExceptions = SQLQueryCommandExecutionException.class)
    public final void commandShouldNotBeExecuted()
            throws DataBaseConnectionPoolAccessConnectionException,
                   SQLQueryCommandExecutionException,
                   SQLException
    {
        final String namesOfSelectedColumns = "employee_id, first_name, last_name, patronymic, salary";
        final String nameOfTable = "not_existing_table";
        final String conditionOfSelection = "salary::numeric > (SELECT AVG(salary::numeric) FROM employee_for_testing)";
        final SQLSelectCommand sqlSelectCommand = new SQLSelectCommand(namesOfSelectedColumns,
                nameOfTable, conditionOfSelection);
        final Connection connectionToDataBase = SQLSelectCommandTest.dataBaseConnectionPool.findAvailableConnection();
        try
        {
            sqlSelectCommand.execute(connectionToDataBase).close();
        }
        finally
        {
            SQLSelectCommandTest.dataBaseConnectionPool.returnConnectionToThePool(connectionToDataBase);
        }
    }
}
