package by.bsu.zuevvlad.seventhlab.entity;

import java.io.Serializable;
import java.util.Objects;

public abstract class Entity implements Serializable
{
    private static final long serialVersionUID = 1L;

    private long id;

    public Entity()
    {
        super();
        this.id = Entity.VALUE_OF_NOT_DEFINED_ID;
    }

    public static final long VALUE_OF_NOT_DEFINED_ID = 0;

    public Entity(final long id)
    {
        super();
        this.id = id;
    }

    public final void setId(final long id)
    {
        this.id = id;
    }

    public final long getId()
    {
        return this.id;
    }

    @Override
    public boolean equals(final Object otherObject)
    {
        if(this == otherObject)
        {
            return true;
        }
        if(otherObject == null)
        {
            return false;
        }
        if(this.getClass() != otherObject.getClass())
        {
            return false;
        }
        final Entity other = (Entity)otherObject;
        return this.id == other.id;
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(this.id);
    }

    @Override
    public String toString()
    {
        return this.getClass().getSimpleName() + "[id = " + this.id + "]";
    }
}
