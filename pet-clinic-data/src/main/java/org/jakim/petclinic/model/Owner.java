package org.jakim.petclinic.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.swing.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Owner
        extends Person
{
    private String address;

    private String city;

    private String telephone;

    @OneToMany( cascade = CascadeType.ALL,
                mappedBy = "owner" )
    private Set<Pet> pets = new HashSet<>( );

    public String getAddress( )
    {
        return address;
    }

    public void setAddress( String address )
    {
        this.address = address;
    }

    public String getCity( )
    {
        return city;
    }

    public void setCity( String city )
    {
        this.city = city;
    }

    public String getTelephone( )
    {
        return telephone;
    }

    public void setTelephone( String telephone )
    {
        this.telephone = telephone;
    }

    public Set<Pet> getPets( )
    {
        return pets;
    }

    public void setPets( Set<Pet> pets )
    {
        this.pets = pets;
    }

    public Owner addPet( final Pet pet )
    {
        pet.setOwner( this );
        pets.add( pet );

        return this;
    }

    public Pet getPet( String name )
    {
       return this.getPet( name, false );
    }
    public Pet getPet( String name,
                       boolean ignoreNew )
    {
        for( Pet pet : this.pets )
        {
            if( !ignoreNew || !pet.isNew( ) )
            {
                if( pet.getName( )
                       .equalsIgnoreCase( name ) )
                {
                    return pet;
                }
            }
        }

        return null;
    }


    // TODO: implement Owner equals and hash methods in order

}
