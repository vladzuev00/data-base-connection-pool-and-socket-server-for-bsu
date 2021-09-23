package by.bsu.zuevvlad.seventhlab.logic.validator.entity.employee;



import by.bsu.zuevvlad.seventhlab.logic.validator.entity.ValidatorForPropertiesOfEntity;

import java.math.BigDecimal;

public class ValidatorForPropertiesOfEmployee extends ValidatorForPropertiesOfEntity
{
    public ValidatorForPropertiesOfEmployee()
    {
        super();
    }

    public final boolean isValidFirstName(final String researchFirstName)
    {
        return researchFirstName.matches(ValidatorForPropertiesOfEmployee.REGULAR_EXPRESSION_FOR_FIRST_NAME);
    }

    private static final String REGULAR_EXPRESSION_FOR_FIRST_NAME = "[-a-zA-Z ']{"
            + ValidatorForPropertiesOfEmployee.MINIMAL_ALLOWABLE_LENGTH_OF_FIRST_NAME + ","
            + ValidatorForPropertiesOfEmployee.MAXIMAL_ALLOWABLE_LENGTH_OF_FIRST_NAME + "}";
    private static final int MINIMAL_ALLOWABLE_LENGTH_OF_FIRST_NAME = 2;
    private static final int MAXIMAL_ALLOWABLE_LENGTH_OF_FIRST_NAME = 30;

    public final boolean isValidLastName(final String researchLastName)
    {
        return researchLastName.matches(ValidatorForPropertiesOfEmployee.REGULAR_EXPRESSION_FOR_LAST_NAME);
    }

    private static final String REGULAR_EXPRESSION_FOR_LAST_NAME = "[-a-zA-Z ']{"
            + ValidatorForPropertiesOfEmployee.MINIMAL_ALLOWABLE_LENGTH_OF_LAST_NAME + ","
            + ValidatorForPropertiesOfEmployee.MAXIMAL_ALLOWABLE_LENGTH_OF_LAST_NAME + "}";
    private static final int MINIMAL_ALLOWABLE_LENGTH_OF_LAST_NAME = 2;
    private static final int MAXIMAL_ALLOWABLE_LENGTH_OF_LAST_NAME = 30;

    public final boolean isValidPatronymic(final String researchPatronymic)
    {
        return researchPatronymic.matches(ValidatorForPropertiesOfEmployee.REGULAR_EXPRESSION_FOR_PATRONYMIC);
    }

    private static final String REGULAR_EXPRESSION_FOR_PATRONYMIC = "[-a-zA-Z ']{"
            + ValidatorForPropertiesOfEmployee.MINIMAL_ALLOWABLE_LENGTH_OF_PATRONYMIC + ","
            + ValidatorForPropertiesOfEmployee.MAXIMAL_ALLOWABLE_LENGTH_OF_PATRONYMIC + "}";
    private static final int MINIMAL_ALLOWABLE_LENGTH_OF_PATRONYMIC = 2;
    private static final int MAXIMAL_ALLOWABLE_LENGTH_OF_PATRONYMIC = 30;

    public final boolean isValidSalary(final BigDecimal researchSalary)
    {
        return     researchSalary.compareTo(ValidatorForPropertiesOfEmployee.MINIMAL_ALLOWABLE_VALUE_OF_SALARY) >= 0
                && researchSalary.compareTo(ValidatorForPropertiesOfEmployee.MAXIMAL_ALLOWABLE_VALUE_OF_SALARY) <= 0;
    }

    public static final BigDecimal MINIMAL_ALLOWABLE_VALUE_OF_SALARY = BigDecimal.ZERO;
    public static final BigDecimal MAXIMAL_ALLOWABLE_VALUE_OF_SALARY
            = new BigDecimal(ValidatorForPropertiesOfEmployee.DESCRIPTION_OF_MAXIMAL_ALLOWABLE_VALUE_OF_SALARY);
    private static final String DESCRIPTION_OF_MAXIMAL_ALLOWABLE_VALUE_OF_SALARY = "999999";
}
