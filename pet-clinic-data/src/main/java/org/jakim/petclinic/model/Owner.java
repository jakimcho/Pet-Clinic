package org.jakim.petclinic.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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


    // TODO: implement Owner equals and hash methods in order


    @Override
    public boolean equals( Object o )
    {
        if( this == o )
        {
            return true;
        }
        if( !( o instanceof Owner ) )
        {
            return false;
        }
        Owner owner = ( Owner ) o;
        return Objects.equals( getAddress( ),
                               owner.getAddress( ) ) &&
               Objects.equals( getTelephone( ),
                               owner.getTelephone( ) ) && Objects.equals( getFirstName( ),
                                                                          owner.getFirstName( ) ) &&
               Objects.equals( getTelephone( ),
                               owner.getTelephone( ) ) && Objects.equals( getLastName( ),
                                                                          owner.getLastName( ) );
    }

    @Override
    public int hashCode( )
    {
        return Objects.hash( getAddress( ),
                             getTelephone( ),
                             getLastName( ),
                             getFirstName( ) );
    }
}
