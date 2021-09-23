package by.bsu.zuevvlad.seventhlab.entity.employee;

public final class Tester extends Employee
{
    private int amountOfRetrievedErrors;

    public Tester()
    {
        super();
        this.amountOfRetrievedErrors = Tester.VALUE_OF_NOT_DEFINED_AMOUNT_OF_NOT_RETRIEVED_ERRORS;
    }

    public static final int VALUE_OF_NOT_DEFINED_AMOUNT_OF_NOT_RETRIEVED_ERRORS = 0;

    public Tester(final long id)
    {
        super(id);
        this.amountOfRetrievedErrors = Tester.VALUE_OF_NOT_DEFINED_AMOUNT_OF_NOT_RETRIEVED_ERRORS;
    }

    public Tester(final int amountOfRetrievedErrors)
    {
        super();
        this.amountOfRetrievedErrors = amountOfRetrievedErrors;
    }

    public Tester(final long id, final int amountOfRetrievedErrors)
    {
        super(id);
        this.amountOfRetrievedErrors = amountOfRetrievedErrors;
    }

    public final void setAmountOfRetrievedErrors(final int amountOfRetrievedErrors)
    {
        this.amountOfRetrievedErrors = amountOfRetrievedErrors;
    }

    public final int getAmountOfRetrievedErrors()
    {
        return this.amountOfRetrievedErrors;
    }

    @Override
    public final boolean equals(final Object otherObject)
    {
        if(!super.equals(otherObject))
        {
            return false;
        }
        final Tester other = (Tester)otherObject;
        return this.amountOfRetrievedErrors == other.amountOfRetrievedErrors;
    }

    @Override
    public final int hashCode()
    {
        return super.hashCode() + Integer.hashCode(this.amountOfRetrievedErrors);
    }

    @Override
    public final String toString()
    {
        return super.toString() + "[amountOfRetrievedErrors = " + this.amountOfRetrievedErrors + "]";
    }
}
