package org.jakim.petclinic.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity
        implements Serializable
{
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    public boolean isNew( )
    {
        return null == this.id;
    }

    public Long getId( )
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    @Override
    public String toString( )
    {
        return "BaseEntity{" +
               "id=" + id +
               '}';
    }
}
