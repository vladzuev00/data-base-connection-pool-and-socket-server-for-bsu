package by.bsu.zuevvlad.seventhlab.entity.employee;

import java.util.Objects;

public final class Analyst extends Employee
{
    private String subjectArea;

    public Analyst()
    {
        super();
        this.subjectArea = Analyst.VALUE_OF_NOT_DEFINED_SUBJECT_AREA;
    }

    public static final String VALUE_OF_NOT_DEFINED_SUBJECT_AREA = "not defined";

    public Analyst(final long id)
    {
        super(id);
        this.subjectArea = Analyst.VALUE_OF_NOT_DEFINED_SUBJECT_AREA;
    }

    public Analyst(final String subjectArea)
    {
        super();
        this.subjectArea = subjectArea;
    }

    public Analyst(final long id, final String subjectArea)
    {
        super(id);
        this.subjectArea = subjectArea;
    }

    public final void setSubjectArea(final String subjectArea)
    {
        this.subjectArea = subjectArea;
    }

    public final String getSubjectArea()
    {
        return this.subjectArea;
    }

    @Override
    public final boolean equals(final Object otherObject)
    {
        if(!super.equals(otherObject))
        {
            return false;
        }
        final Analyst other = (Analyst)otherObject;
        return Objects.equals(this.subjectArea, other.subjectArea);
    }

    @Override
    public final int hashCode()
    {
        return super.hashCode() + Objects.hashCode(this.subjectArea);
    }

    @Override
    public final String toString()
    {
        return super.toString() + "[subjectArea = " + this.subjectArea + "]";
    }
}
