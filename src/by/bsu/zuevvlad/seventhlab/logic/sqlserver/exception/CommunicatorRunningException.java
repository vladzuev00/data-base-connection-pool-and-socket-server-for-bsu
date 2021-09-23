package by.bsu.zuevvlad.seventhlab.logic.sqlserver.exception;

public final class CommunicatorRunningException extends RuntimeException
{
    public CommunicatorRunningException()
    {
        super();
    }

    public CommunicatorRunningException(final String description)
    {
        super(description);
    }

    public CommunicatorRunningException(final Exception cause)
    {
        super(cause);
    }

    public CommunicatorRunningException(final String description, final Exception cause)
    {
        super(description, cause);
    }
}
