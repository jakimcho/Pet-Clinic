package org.jakim.petclinic.model;

import java.util.Set;
import java.util.stream.Collectors;

public class Owner
        extends Person
{

    private String address;
    private String city;
    private String telephone;
    private Set<Pet> pets;

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

    @Override
    public String toString( )
    {
        return super.toString( ) + "Pets: " + pets.stream( )
                                                  .map( Pet::toString )
                                                  .collect( Collectors.joining( ", " ) );
    }
}
