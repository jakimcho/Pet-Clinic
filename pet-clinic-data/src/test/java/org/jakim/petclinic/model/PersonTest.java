package org.jakim.petclinic.model;

import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@Ignore
public class PersonTest<T extends Person>
        extends BaseEntityTest<T>
{

    @Test
    public void getFirstName( )
    {
        String theName = "Ivan";
        this.entity.setFirstName( theName );

        assertThat( this.entity.getFirstName( ) ).as( "First name set and get methods should work" )
                                                 .isEqualTo( theName );
    }

    @Test
    public void getLastName( )
    {
        String lastName = "Ivanov";
        this.entity.setLastName( lastName );

        assertThat( this.entity.getLastName( ) ).as( "Last name set and get methods should work" )
                                                .isEqualTo( lastName );
    }
}