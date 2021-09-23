package by.bsu.zuevvlad.seventhlab.entity.employee;

import by.bsu.zuevvlad.seventhlab.entity.Entity;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Employee extends Entity
{
    private String firstName;
    private String lastName;
    private String patronymic;
    private BigDecimal salary;

    public Employee()
    {
        super();
        this.initializeFieldsByDefault();
    }

    private final void initializeFieldsByDefault()
    {
        this.firstName = Employee.VALUE_OF_NOT_DEFINED_FIRST_NAME;
        this.lastName = Employee.VALUE_OF_NOT_DEFINED_LAST_NAME;
        this.salary = Employee.VALUE_OF_NOT_DEFINED_SALARY;
        this.patronymic = Employee.VALUE_OF_NOT_DEFINED_PATRONYMIC;
    }

    public static final String VALUE_OF_NOT_DEFINED_FIRST_NAME = "not defined";
    public static final String VALUE_OF_NOT_DEFINED_LAST_NAME = "not defined";
    public static final String VALUE_OF_NOT_DEFINED_PATRONYMIC = "not defined";
    public static final BigDecimal VALUE_OF_NOT_DEFINED_SALARY = BigDecimal.ZERO;

    public Employee(final long id)
    {
        super(id);
        this.initializeFieldsByDefault();
    }

    public Employee(final String firstName, final String lastName, final String patronymic, final BigDecimal salary)
    {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.salary = salary;
    }

    public Employee(final long id, final String firstName, final String lastName, final String patronymic,
                    final BigDecimal salary)
    {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.salary = salary;
    }

    public final void setFirstName(final String firstName)
    {
        this.firstName = firstName;
    }

    public final String getFirstName()
    {
        return this.firstName;
    }

    public final void setLastName(final String lastName)
    {
        this.lastName = lastName;
    }

    public final String getLastName()
    {
        return this.lastName;
    }

    public final void setPatronymic(final String patronymic)
    {
        this.patronymic = patronymic;
    }

    public final String getPatronymic()
    {
        return this.patronymic;
    }

    public final void setSalary(final BigDecimal salary)
    {
        this.salary = salary;
    }

    public final BigDecimal getSalary()
    {
        return this.salary;
    }

    @Override
    public boolean equals(final Object otherObject)
    {
        if (!super.equals(otherObject))
        {
            return false;
        }
        final Employee other = (Employee)otherObject;
        return     Objects.equals(this.firstName, other.firstName) && Objects.equals(this.lastName, other.lastName)
                && Objects.equals(this.patronymic, other.patronymic) && Objects.equals(this.salary, other.salary);
    }

    @Override
    public int hashCode()
    {
        return super.hashCode() + Objects.hash(this.firstName, this.lastName, this.patronymic, this.salary);
    }

    @Override
    public String toString()
    {
        return super.toString() + "[firstName = " + this.firstName + ", lastName = " + this.lastName
                + ", patronymic = " + this.patronymic + ", salary = " + this.salary + "]";
    }
}

