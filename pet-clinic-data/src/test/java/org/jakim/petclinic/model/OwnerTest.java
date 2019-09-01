package org.jakim.petclinic.model;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class OwnerTest
{
    private Owner owner;

    @Before
    public void setUp( )
    {
        this.owner = new Owner( );
    }


    @Test
    public void getFirstName( )
    {
        String theName = "Ivan";
        this.owner.setFirstName( theName );

        assertThat( this.owner.getFirstName( ) ).as( "First name set and get methods should work" )
                                                .isEqualTo( theName );
    }

    @Test
    public void getLastName( )
    {
        String lastName = "Ivanov";
        this.owner.setLastName( lastName );

        assertThat( this.owner.getLastName( ) ).as( "Last name set and get methods should work" )
                                               .isEqualTo( lastName );
    }

    @Test
    public void getAddress( )
    {
        String theAddress = "str. Vasil Drumev 51";
        this.owner.setAddress( theAddress );

        assertThat( this.owner.getAddress( ) ).as( "Address set and get methods should work" )
                                              .isEqualTo( theAddress );
    }

    @Test
    public void getCity( )
    {
        String theCity = "Smolyan";
        this.owner.setCity( theCity );

        assertThat( this.owner.getCity( ) ).as( "City set and get methods should work" )
                                           .isEqualTo( theCity );
    }

    @Test
    public void getTelephone( )
    {
        String thePhone = "53453453443";
        this.owner.setTelephone( thePhone );

        assertThat( this.owner.getTelephone( ) ).as( "Telephone set and get methods should work" )
                                                .isEqualTo( thePhone );
    }


    @Test
    public void getPets( )
    {
        Set<Pet> thePets = preparePets( );
        this.owner.setPets( thePets );

        assertThat( this.owner.getPets( ).size() ).as( "There should be only two pets" ).isEqualTo( 2 );

        assertThat( this.owner.getPets( ) ).as( "Telephone set and get methods should work" )
                                           .isEqualTo( thePets );
    }

    private Set<Pet> preparePets( )
    {
        Pet pet1 = new Pet( );
        pet1.setOwner( owner );
        pet1.setId( 1L );
        pet1.setBirthDate( LocalDate.now( ) );

        Pet pet2 = new Pet( );
        pet2.setOwner( owner );
        pet2.setId( 2L );
        pet2.setBirthDate( LocalDate.now( )
                                    .minusYears( 2 ) );

        Set<Pet> pets = new HashSet<>( );
        pets.add( pet1 );
        pets.add( pet2 );
        return pets;
    }

}