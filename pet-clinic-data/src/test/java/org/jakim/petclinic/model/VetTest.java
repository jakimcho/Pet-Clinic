package org.jakim.petclinic.model;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class VetTest
        extends PersonTest<Vet>
{
    @Before
    public void setUp( )
            throws Exception
    {
        this.entity = new Vet( );
    }

    @Test
    public void getSpecialties( )
    {

        Specialty s1 = new Specialty( );
        s1.setDescription( "derma" );
        Specialty s2 = new Specialty( );
        s2.setDescription( "Orto" );

        Set<Specialty> specialties = new HashSet<>( );
        specialties.add( s1 );
        specialties.add( s2 );

        this.entity.setSpecialties( specialties );

        assertThat( entity.getSpecialties( ) ).hasSize( 2 );
        assertThat( entity.getSpecialties( ) ).containsExactly( s1,
                                                                s2 );
    }
}