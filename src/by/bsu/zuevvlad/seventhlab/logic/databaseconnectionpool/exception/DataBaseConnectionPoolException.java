package by.bsu.zuevvlad.seventhlab.logic.databaseconnectionpool.exception;

public class DataBaseConnectionPoolException extends Exception
{
    public DataBaseConnectionPoolException()
    {
        super();
    }

    public DataBaseConnectionPoolException(final String description)
    {
        super(description);
    }

    public DataBaseConnectionPoolException(final Exception cause)
    {
        super(cause);
    }

    public DataBaseConnectionPoolException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
