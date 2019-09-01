package org.jakim.petclinic.model;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class PetTest
        extends BaseEntityTest<Pet>
{

    @Before
    public void setUp( )
            throws Exception
    {
        this.entity = new Pet( );
    }

    @Test
    public void getPetType( )
    {
        PetType petType = new PetType( );
        petType.setName( "Cat" );
        this.entity.setPetType( petType );

        assertEquals( "Pet type mismatch",
                      petType,
                      this.entity.getPetType( ) );
    }

    @Test
    public void getOwner( )
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
    public void getBirthDate( )
    {
        LocalDate bDate = LocalDate.now().minusYears( 2 );
        this.entity.setBirthDate( bDate );

        assertEquals( "Birth date mismatch",
                      bDate,
                      this.entity.getBirthDate( ) );
    }
}