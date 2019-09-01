package org.jakim.petclinic.model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class OwnerTest
        extends PersonTest<Owner>
{
    @Before
    public void setUp( )
    {
        this.entity = new Owner( );
    }

    @Test
    public void getAddress( )
    {
        String theAddress = "str. Vasil Drumev 51";
        this.entity.setAddress( theAddress );

        assertThat( this.entity.getAddress( ) ).as( "Address set and get methods should work" )
                                               .isEqualTo( theAddress );
    }

    @Test
    public void getCity( )
    {
        String theCity = "Smolyan";
        this.entity.setCity( theCity );

        assertThat( this.entity.getCity( ) ).as( "City set and get methods should work" )
                                            .isEqualTo( theCity );
    }

    @Test
    public void getTelephone( )
    {
        String thePhone = "53453453443";
        this.entity.setTelephone( thePhone );

        assertThat( this.entity.getTelephone( ) ).as( "Telephone set and get methods should work" )
                                                 .isEqualTo( thePhone );
    }


    @Test
    public void getPets( )
    {
        Set<Pet> thePets = preparePets( );
        this.entity.setPets( thePets );

        assertThat( this.entity.getPets( )
                               .size( ) ).as( "There should be only two pets" )
                                         .isEqualTo( 2 );

        assertThat( this.entity.getPets( ) ).as( "Telephone set and get methods should work" )
                                            .isEqualTo( thePets );
    }

    private Set<Pet> preparePets( )
    {
        Pet pet1 = new Pet( );
        pet1.setOwner( entity );
        pet1.setId( 1L );
        pet1.setBirthDate( LocalDate.now( ) );

        Pet pet2 = new Pet( );
        pet2.setOwner( entity );
        pet2.setId( 2L );
        pet2.setBirthDate( LocalDate.now( )
                                    .minusYears( 2 ) );

        Set<Pet> pets = new HashSet<>( );
        pets.add( pet1 );
        pets.add( pet2 );
        return pets;
    }

}