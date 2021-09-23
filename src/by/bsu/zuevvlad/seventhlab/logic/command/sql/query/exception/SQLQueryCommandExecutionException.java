package by.bsu.zuevvlad.seventhlab.logic.command.sql.query.exception;

import by.bsu.zuevvlad.seventhlab.logic.command.sql.exception.SQLCommandExecutionException;

public final class SQLQueryCommandExecutionException extends SQLCommandExecutionException
{
    public SQLQueryCommandExecutionException()
    {
        super();
    }

    public SQLQueryCommandExecutionException(final String description)
    {
        super(description);
    }

    public SQLQueryCommandExecutionException(final Exception cause)
    {
        super(cause);
    }

    public SQLQueryCommandExecutionException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
