package by.bsu.zuevvlad.seventhlab.logic.sqlserver.exception;

public final class SQLServerCreatingException extends SQLServerException
{
    public SQLServerCreatingException()
    {
        super();
    }

    public SQLServerCreatingException(final String description)
    {
        super(description);
    }

    public SQLServerCreatingException(final Exception cause)
    {
        super(cause);
    }

    public SQLServerCreatingException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
