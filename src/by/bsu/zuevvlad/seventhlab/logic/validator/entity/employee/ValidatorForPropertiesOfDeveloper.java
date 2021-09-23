package by.bsu.zuevvlad.seventhlab.logic.validator.entity.employee;

import by.bsu.zuevvlad.seventhlab.entity.employee.Developer;

public final class ValidatorForPropertiesOfDeveloper extends ValidatorForPropertiesOfEmployee
{
    public ValidatorForPropertiesOfDeveloper()
    {
        super();
    }

    public final boolean isValidQualification(final Developer.DeveloperQualification researchQualification)
    {
        return researchQualification != Developer.DeveloperQualification.NOT_DEFINED;
    }
}
