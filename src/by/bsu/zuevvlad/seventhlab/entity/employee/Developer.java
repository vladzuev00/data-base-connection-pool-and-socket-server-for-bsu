package by.bsu.zuevvlad.seventhlab.entity.employee;

import java.util.Objects;

public final class Developer extends Employee
{
    private DeveloperQualification qualification;

    public static enum DeveloperQualification
    {
        NOT_DEFINED, JUNIOR, MIDDLE, SENIOR
    }

    public Developer()
    {
        super();
        this.qualification = Developer.VALUE_OF_NOT_DEFINED_QUALIFICATION;
    }

    public static final DeveloperQualification VALUE_OF_NOT_DEFINED_QUALIFICATION
            = DeveloperQualification.NOT_DEFINED;

    public Developer(final long id)
    {
        super(id);
        this.qualification = Developer.VALUE_OF_NOT_DEFINED_QUALIFICATION;
    }

    public Developer(final DeveloperQualification qualification)
    {
        super();
        this.qualification = qualification;
    }

    public Developer(final long id, final DeveloperQualification qualification)
    {
        super(id);
        this.qualification = qualification;
    }

    public final void setQualification(final DeveloperQualification qualification)
    {
        this.qualification = qualification;
    }

    public final DeveloperQualification getQualification()
    {
        return this.qualification;
    }

    @Override
    public final boolean equals(final Object otherObject)
    {
        if(!super.equals(otherObject))
        {
            return false;
        }
        final Developer other = (Developer)otherObject;
        return this.qualification == other.qualification;
    }

    @Override
    public final int hashCode()
    {
        return super.hashCode() + Objects.hashCode(this.qualification);
    }

    @Override
    public final String toString()
    {
        return super.toString() + "[qualification = " + this.qualification + "]";
    }
}
