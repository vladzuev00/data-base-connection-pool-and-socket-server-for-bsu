package by.bsu.zuevvlad.seventhlab.logic.validator.entity.employee;

public final class ValidatorForPropertiesOfTester extends ValidatorForPropertiesOfEmployee
{
    public ValidatorForPropertiesOfTester()
    {
        super();
    }

    public final boolean isValidAmountOfRetrievedErrors(final int researchAmountOfRetrievedErrors)
    {
        return     ValidatorForPropertiesOfTester.MINIMAL_ALLOWABLE_VALUE_OF_AMOUNT_OF_RETRIEVED_ERRORS <= researchAmountOfRetrievedErrors
                && researchAmountOfRetrievedErrors <= ValidatorForPropertiesOfTester.MAXIMAL_ALLOWABLE_VALUE_OF_AMOUNT_OF_RETRIEVED_ERRORS;
    }

    private static final int MINIMAL_ALLOWABLE_VALUE_OF_AMOUNT_OF_RETRIEVED_ERRORS = 0;
    private static final int MAXIMAL_ALLOWABLE_VALUE_OF_AMOUNT_OF_RETRIEVED_ERRORS = Integer.MAX_VALUE;
}
