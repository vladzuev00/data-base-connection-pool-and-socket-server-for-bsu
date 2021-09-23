package by.bsu.zuevvlad.seventhlab.logic.validator.entity;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidatorForPropertiesOfEntityTest
{
    public ValidatorForPropertiesOfEntityTest()
    {
        super();
    }

    @Test
    public final void researchIdShouldBeValid()
    {
        final long researchId = ValidatorForPropertiesOfEntity.MINIMAL_ALLOWABLE_VALUE_OF_ID;
        final ValidatorForPropertiesOfEntity validatorForPropertiesOfEntity = new ValidatorForPropertiesOfEntity();
        Assert.assertTrue(validatorForPropertiesOfEntity.isValidId(researchId));
    }

    @Test
    public final void researchIdShouldNotBeValid()
    {
        final long researchId = ValidatorForPropertiesOfEntity.MINIMAL_ALLOWABLE_VALUE_OF_ID - 1;
        final ValidatorForPropertiesOfEntity validatorForPropertiesOfEntity = new ValidatorForPropertiesOfEntity();
        Assert.assertFalse(validatorForPropertiesOfEntity.isValidId(researchId));
    }
}
