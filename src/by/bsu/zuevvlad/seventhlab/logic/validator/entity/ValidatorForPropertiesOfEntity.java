package by.bsu.zuevvlad.seventhlab.logic.validator.entity;

public class ValidatorForPropertiesOfEntity
{
    public ValidatorForPropertiesOfEntity()
    {
        super();
    }

    public final boolean isValidId(final long researchId)
    {
        return     ValidatorForPropertiesOfEntity.MINIMAL_ALLOWABLE_VALUE_OF_ID <= researchId
                && researchId <= ValidatorForPropertiesOfEntity.MAXIMAL_ALLOWABLE_VALUE_OF_ID;
    }

    public static final long MINIMAL_ALLOWABLE_VALUE_OF_ID = 0;
    public static final long MAXIMAL_ALLOWABLE_VALUE_OF_ID = Long.MAX_VALUE;
}
