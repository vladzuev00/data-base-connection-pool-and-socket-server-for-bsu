package by.bsu.zuevvlad.seventhlab.logic.sqlserver.exception;

public class SQLServerException extends Exception
{
    public SQLServerException()
    {
        super();
    }

    public SQLServerException(final String description)
    {
        super(description);
    }

    public SQLServerException(final Exception cause)
    {
        super(cause);
    }

    public SQLServerException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
