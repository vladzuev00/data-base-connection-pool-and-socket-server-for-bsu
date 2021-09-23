package by.bsu.zuevvlad.seventhlab.logic.command.sql.exception;

public class SQLCommandExecutionException extends Exception
{
    public SQLCommandExecutionException()
    {
        super();
    }

    public SQLCommandExecutionException(final String description)
    {
        super(description);
    }

    public SQLCommandExecutionException(final Exception cause)
    {
        super(cause);
    }

    public SQLCommandExecutionException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
