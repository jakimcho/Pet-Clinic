package org.jakim.petclinic.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

class PetTest
        extends BaseEntityTest<Pet>
{

    @BeforeEach
    void setUp( )
            throws Exception
    {
        this.entity = new Pet( );
    }

    @Test
    void getPetType( )
    {
        PetType petType = new PetType( );
        petType.setName( "Cat" );
        this.entity.setPetType( petType );

        assertEquals( "Pet type mismatch",
                      petType,
                      this.entity.getPetType( ) );
    }

    @Test
    void getOwner( )
    {
        Owner owner = new Owner( );
        owner.setFirstName( "Ivan" );
        owner.setLastName( "Ivanov" );
        this.entity.setOwner( owner );

        assertEquals( "Owner mismatch",
                      owner,
                      this.entity.getOwner( ) );
    }

    @Test
    void getBirthDate( )
    {
        LocalDate bDate = LocalDate.now( )
                                   .minusYears( 2 );
        this.entity.setBirthDate( bDate );

        assertEquals( "Birth date mismatch",
                      bDate,
                      this.entity.getBirthDate( ) );
    }

    @Test
    void getName( )
    {
        String animalName = "Milinka";
        this.entity.setName( animalName );

        assertThat( this.entity.getName( ),
                    is( equalTo( animalName ) ) );
    }

    @Test
    void getVisitsShouldNotBeNull( )
    {
        assertThat( this.entity.getVisits( ),
                    is( notNullValue( ) ) );

        assertThat( this.entity.getVisits( ),
                    is( empty( ) ) );
    }

    @Test
    void getExistingVisits( )
    {
        Set<Visit> visits = new HashSet<>( );
        Visit visit = new Visit( );
        visit.setId( 1L );
        visits.add( visit );
        this.entity.setVisits( visits );
        assertThat( this.entity.getVisits( ),
                    contains( visit ) );

    }

    @Test
    void addVisit( )
    {
        this.entity.setId( 2L );
        Visit visit = new Visit( );
        visit.setId( 1L );
        this.entity.addVisit( visit );
        assertThat( visit.getPet( )
                         .getId( ),
                    is( equalTo( this.entity.getId( ) ) ) );
        assertThat( this.entity.getVisits( ),
                    hasSize( 1 ) );
        assertThat( this.entity.getVisits( ),
                    contains( visit ) );
    }
}