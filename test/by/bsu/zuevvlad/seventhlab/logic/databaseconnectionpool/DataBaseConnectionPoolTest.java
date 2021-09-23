package by.bsu.zuevvlad.seventhlab.logic.databaseconnectionpool;

import by.bsu.zuevvlad.seventhlab.logic.databaseconnectionpool.exception.DataBaseConnectionPoolAccessConnectionException;
import by.bsu.zuevvlad.seventhlab.logic.databaseconnectionpool.exception.DataBaseConnectionPoolCreatingException;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.Connection;

public final class DataBaseConnectionPoolTest
{
    public DataBaseConnectionPoolTest()
    {
        super();
    }

    @Test
    public final void dataBaseConnectionPoolShouldBeCreated()
            throws DataBaseConnectionPoolCreatingException,
                   IOException
    {
        final DataBaseConnectionPool dataBaseConnectionPool
                = new DataBaseConnectionPool(DataBaseConnectionPoolTest.URL_OF_DATA_BASE,
                DataBaseConnectionPoolTest.USER_OF_DATA_BASE, DataBaseConnectionPoolTest.PASSWORD_OF_DATA_BASE);
        try
        {
            final int expectedAmountOfAvailableConnections = DataBaseConnectionPool.DEFAULT_AMOUNT_OF_INVOLVED_CONNECTIONS;
            final int actualAmountOfAvailableConnections = dataBaseConnectionPool.findAmountOfAvailableConnections();
            Assert.assertEquals(actualAmountOfAvailableConnections, expectedAmountOfAvailableConnections);
        }
        finally
        {
            dataBaseConnectionPool.close();
        }
    }

    private static final String URL_OF_DATA_BASE = "jdbc:postgresql://localhost:5432/employee_data_base";
    private static final String USER_OF_DATA_BASE = "postgres";
    private static final String PASSWORD_OF_DATA_BASE = "kakawka228";

    @Test(expectedExceptions = DataBaseConnectionPoolCreatingException.class)
    public final void dataBaseConnectionPoolShouldNotBeCreated()
            throws DataBaseConnectionPoolCreatingException,
                   IOException
    {
        final String wrongPasswordOfDataBase = "wrong_password";
        final DataBaseConnectionPool dataBaseConnectionPool
                = new DataBaseConnectionPool(DataBaseConnectionPoolTest.URL_OF_DATA_BASE,
                DataBaseConnectionPoolTest.USER_OF_DATA_BASE, wrongPasswordOfDataBase);
        dataBaseConnectionPool.close();
    }

    @Test
    public final void dataBaseConnectionPoolShouldBeCreatedWithAmountOfInvolvedConnections()
            throws IOException
    {
        final int amountOfInvolvedConnections = 1;
        boolean exceptionIsArisen = false;
        try
        {
            final DataBaseConnectionPool dataBaseConnectionPool
                    = new DataBaseConnectionPool(DataBaseConnectionPoolTest.URL_OF_DATA_BASE,
                    DataBaseConnectionPoolTest.USER_OF_DATA_BASE,
                    DataBaseConnectionPoolTest.PASSWORD_OF_DATA_BASE,
                    amountOfInvolvedConnections);
            dataBaseConnectionPool.close();
        }
        catch(final DataBaseConnectionPoolCreatingException notExpectedException)
        {
            exceptionIsArisen = true;
        }
        Assert.assertFalse(exceptionIsArisen);
    }

    @Test(expectedExceptions = DataBaseConnectionPoolCreatingException.class)
    public final void dataBaseConnectionPoolShouldNotBeCreatedWithAmountOfInvolvedConnections()
            throws DataBaseConnectionPoolCreatingException, IOException
    {
        final int amountOfInvolvedConnections = 0;
        final DataBaseConnectionPool dataBaseConnectionPool
                = new DataBaseConnectionPool(DataBaseConnectionPoolTest.URL_OF_DATA_BASE,
                DataBaseConnectionPoolTest.USER_OF_DATA_BASE,
                DataBaseConnectionPoolTest.PASSWORD_OF_DATA_BASE,
                amountOfInvolvedConnections);
        dataBaseConnectionPool.close();
    }

    @Test
    public final void amountOfAvailableConnectionsShouldBeFound()
            throws DataBaseConnectionPoolCreatingException,
                   DataBaseConnectionPoolAccessConnectionException,
                   IOException
    {
        final int amountOfInvolvedConnections = 1;
        final DataBaseConnectionPool dataBaseConnectionPool
                = new DataBaseConnectionPool(DataBaseConnectionPoolTest.URL_OF_DATA_BASE,
                                             DataBaseConnectionPoolTest.USER_OF_DATA_BASE,
                                             DataBaseConnectionPoolTest.PASSWORD_OF_DATA_BASE,
                                             amountOfInvolvedConnections);
        Connection gotConnection = null;
        try
        {
            gotConnection = dataBaseConnectionPool.findAvailableConnection();
            Assert.assertEquals(amountOfInvolvedConnections,
                    dataBaseConnectionPool.findAmountOfAvailableConnections() + 1);
        }
        finally
        {
            if(gotConnection != null)
            {
                dataBaseConnectionPool.returnConnectionToThePool(gotConnection);
            }
            dataBaseConnectionPool.close();
        }
    }

    @Test
    public final void availableConnectionShouldBeFound()
            throws DataBaseConnectionPoolCreatingException,
                   IOException
    {
        final DataBaseConnectionPool dataBaseConnectionPool
                = new DataBaseConnectionPool(DataBaseConnectionPoolTest.URL_OF_DATA_BASE,
                DataBaseConnectionPoolTest.USER_OF_DATA_BASE,
                DataBaseConnectionPoolTest.PASSWORD_OF_DATA_BASE);
        boolean exceptionIsArisen = false;
        try
        {
            Connection gotConnection = dataBaseConnectionPool.findAvailableConnection();
            dataBaseConnectionPool.returnConnectionToThePool(gotConnection);
        }
        catch(final DataBaseConnectionPoolAccessConnectionException notExpectedException)
        {
            exceptionIsArisen = true;
        }
        finally
        {
            dataBaseConnectionPool.close();
        }
        Assert.assertFalse(exceptionIsArisen);
    }

    @Test
    public final void availableConnectionShouldNotBeFound()
            throws DataBaseConnectionPoolCreatingException,
                   IOException,
                   DataBaseConnectionPoolAccessConnectionException
    {
        final int amountOfInvolvedConnections = 1;
        final DataBaseConnectionPool dataBaseConnectionPool
                = new DataBaseConnectionPool(DataBaseConnectionPoolTest.URL_OF_DATA_BASE,
                DataBaseConnectionPoolTest.USER_OF_DATA_BASE,
                DataBaseConnectionPoolTest.PASSWORD_OF_DATA_BASE,
                amountOfInvolvedConnections);
        boolean exceptionIsArisen = false;
        try
        {
            final Connection gotConnection = dataBaseConnectionPool.findAvailableConnection();
            try
            {
                dataBaseConnectionPool.findAvailableConnection();
            }
            catch(final DataBaseConnectionPoolAccessConnectionException expectedException)
            {
                exceptionIsArisen = true;
            }
            finally
            {
                dataBaseConnectionPool.returnConnectionToThePool(gotConnection);
            }
        }
        finally
        {
            dataBaseConnectionPool.close();
        }
        Assert.assertTrue(exceptionIsArisen);
    }

    @Test
    public final void connectionShouldBeReturnedToPool()
            throws DataBaseConnectionPoolCreatingException, IOException,
                   DataBaseConnectionPoolAccessConnectionException
    {
        int amountOfInvolvedConnections = 5;
        final DataBaseConnectionPool dataBaseConnectionPool
                = new DataBaseConnectionPool(DataBaseConnectionPoolTest.URL_OF_DATA_BASE,
                DataBaseConnectionPoolTest.USER_OF_DATA_BASE,
                DataBaseConnectionPoolTest.PASSWORD_OF_DATA_BASE,
                amountOfInvolvedConnections);
        try
        {
            final Connection gotConnection = dataBaseConnectionPool.findAvailableConnection();
            dataBaseConnectionPool.returnConnectionToThePool(gotConnection);
            Assert.assertEquals(dataBaseConnectionPool.findAmountOfAvailableConnections(), amountOfInvolvedConnections);
        }
        finally
        {
            dataBaseConnectionPool.close();
        }
    }

    @Test
    public final void dataBaseConnectionPoolShouldBeClose()
            throws DataBaseConnectionPoolCreatingException,
                   IOException
    {
        final DataBaseConnectionPool dataBaseConnectionPool
                = new DataBaseConnectionPool(DataBaseConnectionPoolTest.URL_OF_DATA_BASE,
                DataBaseConnectionPoolTest.USER_OF_DATA_BASE,
                DataBaseConnectionPoolTest.PASSWORD_OF_DATA_BASE);
        dataBaseConnectionPool.close();
        Assert.assertTrue(true);
    }
}
