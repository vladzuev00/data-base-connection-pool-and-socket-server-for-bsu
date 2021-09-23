package by.bsu.zuevvlad.seventhlab.logic.validator.entity.employee;

public final class ValidatorForPropertiesOfAnalyst extends ValidatorForPropertiesOfEmployee
{
    public ValidatorForPropertiesOfAnalyst()
    {
        super();
    }

    public final boolean isValidSubjectArea(final String researchSubjectArea)
    {
        return researchSubjectArea.matches(ValidatorForPropertiesOfAnalyst.REGULAR_EXPRESSION_OF_SUBJECT_AREA);
    }

    private static final String REGULAR_EXPRESSION_OF_SUBJECT_AREA = "[-a-zA-Z '0-9,.]{"
            + ValidatorForPropertiesOfAnalyst.MINIMAL_ALLOWABLE_LENGTH_OF_SUBJECT_AREA + ","
            + ValidatorForPropertiesOfAnalyst.MAXIMAL_ALLOWABLE_LENGTH_OF_SUBJECT_AREA + "}";
    private static final int MINIMAL_ALLOWABLE_LENGTH_OF_SUBJECT_AREA = 5;
    private static final int MAXIMAL_ALLOWABLE_LENGTH_OF_SUBJECT_AREA = 30;
}
