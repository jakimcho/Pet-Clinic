package org.jakim.petclinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Owner
        extends Person
{
    @Getter
    @Setter
    private String address;

    @Getter
    @Setter
    private String city;

    @Getter
    @Setter
    private String telephone;

    @OneToMany( cascade = CascadeType.ALL,
                mappedBy = "owner" )
    @Getter
    @Setter
    private Set<Pet> pets = new HashSet<>( );

    public Owner addPet( final Pet pet )
    {
        pet.setOwner( this );
        pets.add( pet );

        return this;
    }

    public Pet getPet( String name )
    {
        return this.getPet( name,
                            false );
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
