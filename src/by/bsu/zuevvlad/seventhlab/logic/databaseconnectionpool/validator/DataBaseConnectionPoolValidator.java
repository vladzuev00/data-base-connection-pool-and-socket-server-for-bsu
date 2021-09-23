package by.bsu.zuevvlad.seventhlab.logic.databaseconnectionpool.validator;

public final class DataBaseConnectionPoolValidator
{
    public DataBaseConnectionPoolValidator()
    {
        super();
    }

    public final boolean isValidAmountOfInvolvedConnections(final int amountOfInvolvedConnections)
    {
        return DataBaseConnectionPoolValidator.MINIMAL_ALLOWABLE_AMOUNT_OF_INVOLVED_CONNECTIONS <= amountOfInvolvedConnections
                && amountOfInvolvedConnections <= DataBaseConnectionPoolValidator.MAXIMAL_ALLOWABLE_AMOUNT_OF_INVOLVED_CONNECTIONS;
    }

    public static final int MINIMAL_ALLOWABLE_AMOUNT_OF_INVOLVED_CONNECTIONS = 1;
    public static final int MAXIMAL_ALLOWABLE_AMOUNT_OF_INVOLVED_CONNECTIONS = 100;
}
