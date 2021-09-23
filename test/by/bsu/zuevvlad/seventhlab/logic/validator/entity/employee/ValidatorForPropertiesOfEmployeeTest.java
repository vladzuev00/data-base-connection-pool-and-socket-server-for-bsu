package by.bsu.zuevvlad.seventhlab.logic.validator.entity.employee;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public final class ValidatorForPropertiesOfEmployeeTest
{
    public ValidatorForPropertiesOfEmployeeTest()
    {
        super();
    }

    @Test
    public final void firstNameShouldBeValid()
    {
        final String researchFirstName = "name name-name'name";
        final ValidatorForPropertiesOfEmployee validatorForPropertiesOfEmployee
                = new ValidatorForPropertiesOfEmployee();
        Assert.assertTrue(validatorForPropertiesOfEmployee.isValidFirstName(researchFirstName));
    }

    @Test
    public final void firstNameShouldNotBeValid()
    {
        final String tooShortFirstName = "g";
        final ValidatorForPropertiesOfEmployee validatorForPropertiesOfEmployee
                = new ValidatorForPropertiesOfEmployee();
        Assert.assertFalse(validatorForPropertiesOfEmployee.isValidFirstName(tooShortFirstName));
    }

    @Test
    public final void lastNameShouldBeValid()
    {
        final String researchLastName = "lastName'lastName-";
        final ValidatorForPropertiesOfEmployee validatorForPropertiesOfEmployee
                = new ValidatorForPropertiesOfEmployee();
        Assert.assertTrue(validatorForPropertiesOfEmployee.isValidLastName(researchLastName));
    }

    @Test
    public final void lastNameShouldNotBeValid()
    {
        final String tooShortLastName = "g";
        final ValidatorForPropertiesOfEmployee validatorForPropertiesOfEmployee
                = new ValidatorForPropertiesOfEmployee();
        Assert.assertFalse(validatorForPropertiesOfEmployee.isValidLastName(tooShortLastName));
    }

    @Test
    public final void patronymicShouldBeValid()
    {
        final String researchPatronymic = "patronymic-patronymic'";
        final ValidatorForPropertiesOfEmployee validatorForPropertiesOfEmployee
                = new ValidatorForPropertiesOfEmployee();
        Assert.assertTrue(validatorForPropertiesOfEmployee.isValidPatronymic(researchPatronymic));
    }

    @Test
    public final void patronymicShouldNotBeValid()
    {
        final String tooShortPatronymic = "g";
        final ValidatorForPropertiesOfEmployee validatorForPropertiesOfEmployee
                = new ValidatorForPropertiesOfEmployee();
        Assert.assertFalse(validatorForPropertiesOfEmployee.isValidPatronymic(tooShortPatronymic));
    }

    @Test
    public final void salaryShouldBeValid()
    {
        final BigDecimal researchSalary = ValidatorForPropertiesOfEmployee.MAXIMAL_ALLOWABLE_VALUE_OF_SALARY;
        final ValidatorForPropertiesOfEmployee validatorForPropertiesOfEmployee
                = new ValidatorForPropertiesOfEmployee();
        Assert.assertTrue(validatorForPropertiesOfEmployee.isValidSalary(researchSalary));
    }

    @Test
    public final void salaryShouldNotBeValid()
    {
        final String descriptionOfResearchSalary = "-1000000.00";
        final BigDecimal researchSalary = new BigDecimal(descriptionOfResearchSalary);
        final ValidatorForPropertiesOfEmployee validatorForPropertiesOfEmployee
                = new ValidatorForPropertiesOfEmployee();
        Assert.assertFalse(validatorForPropertiesOfEmployee.isValidSalary(researchSalary));
    }
}
