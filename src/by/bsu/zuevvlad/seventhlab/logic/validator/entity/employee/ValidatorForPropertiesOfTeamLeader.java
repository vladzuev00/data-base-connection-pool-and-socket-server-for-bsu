package by.bsu.zuevvlad.seventhlab.logic.validator.entity.employee;

import by.bsu.zuevvlad.seventhlab.entity.employee.Employee;
import by.bsu.zuevvlad.seventhlab.entity.employee.TeamLeader;
import java.util.List;

public final class ValidatorForPropertiesOfTeamLeader extends ValidatorForPropertiesOfEmployee
{
    public ValidatorForPropertiesOfTeamLeader()
    {
        super();
    }

    public final boolean areValidSubordinates(final List<Employee> researchSubordinates,
                                              final TeamLeader teamLeader)
    {
        for(final Employee subordinate : researchSubordinates)
        {
            if(subordinate == teamLeader)
            {
                return false;
            }
        }
        return true;
    }
}
