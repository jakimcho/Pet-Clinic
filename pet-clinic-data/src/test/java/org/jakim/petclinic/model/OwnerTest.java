package org.jakim.petclinic.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class OwnerTest
        extends PersonTest<Owner>
{
    @BeforeEach
    void setUp( )
    {
        this.entity = new Owner( );
    }

    @Test
    void getAddress( )
    {
        String theAddress = "str. Vasil Drumev 51";
        this.entity.setAddress( theAddress );

        assertThat( this.entity.getAddress( ) ).as( "Address set and get methods should work" )
                                               .isEqualTo( theAddress );
    }

    @Test
    void getCity( )
    {
        String theCity = "Smolyan";
        this.entity.setCity( theCity );

        assertThat( this.entity.getCity( ) ).as( "City set and get methods should work" )
                                            .isEqualTo( theCity );
    }

    @Test
    void getTelephone( )
    {
        String thePhone = "53453453443";
        this.entity.setTelephone( thePhone );

        assertThat( this.entity.getTelephone( ) ).as( "Telephone set and get methods should work" )
                                                 .isEqualTo( thePhone );
    }


    @Test
    void getPets( )
    {
        Set<Pet> thePets = preparePets( );
        this.entity.setPets( thePets );

        assertThat( this.entity.getPets( )
                               .size( ) ).as( "There should be only two pets" )
                                         .isEqualTo( 2 );

        assertThat( this.entity.getPets( ) ).as( "Telephone set and get methods should work" )
                                            .isEqualTo( thePets );
    }

    @Test
    void getPet_newdPet_withNoIgnore( )
    {
        Set<Pet> thePets = preparePets( );
        Pet newPet = new Pet( );
        newPet.setName( "Mitio" );
        newPet.setOwner( entity );
        thePets.add( newPet );
        this.entity.setPets( thePets );

        Pet actualPet = this.entity.getPet( "Mitio",
                                            false );

        assertThat( actualPet ).as( "There should be a new pet named Mitio" )
                               .isNotNull( );

        assertThat( actualPet ).as( "New pet is a pet with null id" )
                               .hasFieldOrPropertyWithValue( "id",
                                                             null );
    }

    @Test
    void getPet_newPet_withIgnoreNew( )
    {
        Set<Pet> thePets = preparePets( );
        Pet newPet = new Pet( );
        newPet.setName( "Mitio" );
        newPet.setOwner( entity );
        thePets.add( newPet );
        this.entity.setPets( thePets );

        Pet actualPet = this.entity.getPet( "Mitio",
                                            true );

        assertThat( actualPet ).as( "Mitio should not be return as hi is new" )
                               .isNull( );
    }

    @Test
    void getPet_oldPet_withIgnoreNew( )
    {
        Set<Pet> thePets = preparePets( );
        this.entity.setPets( thePets );

        Pet actualPet = this.entity.getPet( "Lili",
                                            true );

        assertThat( actualPet ).as( "Lili should not be return as she is old pet" )
                               .isNotNull( );

        assertThat( actualPet ).as( "Lili id is 2l" )
                               .hasFieldOrPropertyWithValue( "id",
                                                             2l );
    }

    @Test
    void getPet( )
    {
        Set<Pet> thePets = preparePets( );
        this.entity.setPets( thePets );

        Pet actualPet = this.entity.getPet( "Lili" );

        assertThat( actualPet ).as( "Lili should not be return as she is old pet" )
                               .isNotNull( );

        assertThat( actualPet ).as( "Lili id is 2l" )
                               .hasFieldOrPropertyWithValue( "id",
                                                             2l );
    }

    private Set<Pet> preparePets( )
    {
        Pet pet1 = new Pet( );
        pet1.setName( "Djeik" );
        pet1.setOwner( entity );
        pet1.setId( 1L );
        pet1.setBirthDate( LocalDate.now( ) );

        Pet pet2 = new Pet( );
        pet2.setName( "Lili" );
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