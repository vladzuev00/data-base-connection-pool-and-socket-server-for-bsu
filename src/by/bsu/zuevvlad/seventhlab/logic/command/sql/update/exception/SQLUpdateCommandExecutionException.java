package by.bsu.zuevvlad.seventhlab.logic.command.sql.update.exception;

import by.bsu.zuevvlad.seventhlab.logic.command.sql.exception.SQLCommandExecutionException;

public final class SQLUpdateCommandExecutionException extends SQLCommandExecutionException
{
    public SQLUpdateCommandExecutionException()
    {
        super();
    }

    public SQLUpdateCommandExecutionException(final String description)
    {
        super(description);
    }

    public SQLUpdateCommandExecutionException(final Exception cause)
    {
        super(cause);
    }

    public SQLUpdateCommandExecutionException(final String description, final Exception cause)
    {
        super(description, cause);
    }

}
