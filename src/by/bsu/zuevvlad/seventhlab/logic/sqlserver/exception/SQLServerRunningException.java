package by.bsu.zuevvlad.seventhlab.logic.sqlserver.exception;

public final class SQLServerRunningException extends RuntimeException
{
    public SQLServerRunningException()
    {
        super();
    }

    public SQLServerRunningException(final String description)
    {
        super(description);
    }

    public SQLServerRunningException(final Exception cause)
    {
        super(cause);
    }

    public SQLServerRunningException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
