package by.bsu.zuevvlad.seventhlab.logic.databaseconnectionpool.validator;

import org.testng.Assert;
import org.testng.annotations.Test;

public final class DataBaseConnectionPoolValidatorTest
{
    public DataBaseConnectionPoolValidatorTest()
    {
        super();
    }

    @Test
    public final void amountOfInvolvedConnectionsShouldBeValid()
    {
        final int researchAmountOfInvolvedConnections
                = DataBaseConnectionPoolValidator.MAXIMAL_ALLOWABLE_AMOUNT_OF_INVOLVED_CONNECTIONS;
        final DataBaseConnectionPoolValidator dataBaseConnectionPoolValidator
                = new DataBaseConnectionPoolValidator();
        Assert.assertTrue(dataBaseConnectionPoolValidator
                .isValidAmountOfInvolvedConnections(researchAmountOfInvolvedConnections));
    }

    @Test
    public final void amountOfInvolvedConnectionsShouldNotBeValid()
    {
        final int researchAmountOfInvolvedConnections
                = DataBaseConnectionPoolValidator.MAXIMAL_ALLOWABLE_AMOUNT_OF_INVOLVED_CONNECTIONS + 1;
        final DataBaseConnectionPoolValidator dataBaseConnectionPoolValidator
                = new DataBaseConnectionPoolValidator();
        Assert.assertFalse(dataBaseConnectionPoolValidator
                .isValidAmountOfInvolvedConnections(researchAmountOfInvolvedConnections));
    }
}
