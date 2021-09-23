package by.bsu.zuevvlad.seventhlab.entity.employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class TeamLeader extends Employee
{
    private List<Employee> subordinates;

    public TeamLeader()
    {
        super();
        this.subordinates = new ArrayList<Employee>();
    }

    public TeamLeader(final long id)
    {
        super(id);
        this.subordinates = new ArrayList<Employee>();
    }

    public TeamLeader(final List<Employee> subordinates)
    {
        super();
        this.subordinates = subordinates;
    }

    public TeamLeader(final long id, final List<Employee> subordinates)
    {
        super(id);
        this.subordinates = subordinates;
    }

    public final void setSubordinates(final List<Employee> subordinates)
    {
        this.subordinates = subordinates;
    }

    public final List<Employee> getSubordinates()
    {
        return this.subordinates;
    }

    @Override
    public final boolean equals(final Object otherObject)
    {
        if(!super.equals(otherObject))
        {
            return false;
        }
        final TeamLeader other = (TeamLeader)otherObject;
        return Objects.equals(this.subordinates, other.subordinates);
    }

    @Override
    public final int hashCode()
    {
        return super.hashCode() + this.subordinates.hashCode();
    }

    @Override
    public final String toString()
    {
        return super.toString() + "[subordinates = " + Objects.toString(this.subordinates) + "]";
    }
}
