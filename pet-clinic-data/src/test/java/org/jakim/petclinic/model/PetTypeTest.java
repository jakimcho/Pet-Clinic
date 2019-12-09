package org.jakim.petclinic.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PetTypeTest
        extends BaseEntityTest<PetType>
{

    @BeforeEach
    public void setUp( )
    {
        this.entity = new PetType( );
    }

    @Test
    void toStringTest( )
    {
        entity.setName( "Dog" );
        Assertions.assertThat( this.entity.toString( ) )
                  .contains( "Dog" );
    }

    @Test
    public void getName( )
    {
        String theName = "Dog";
        entity.setName( theName );

        assertThat( this.entity.getName( ) ).isEqualTo( theName );
    }
}