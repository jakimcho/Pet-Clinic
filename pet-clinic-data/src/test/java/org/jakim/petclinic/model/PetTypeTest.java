package org.jakim.petclinic.model;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PetTypeTest
{

    private PetType petType;

    @Before
    public void setUp( )
            throws Exception
    {
        this.petType = new PetType( );
    }

    @Test
    public void getId( )
    {
        petType.setId( 1l );

        assertThat( this.petType.getId( ) ).isEqualTo( 1l );
    }

    @Test
    public void getName( )
    {
        String theName = "Dog";
        petType.setName( theName );

        assertThat( this.petType.getName( ) ).isEqualTo( theName );
    }
}