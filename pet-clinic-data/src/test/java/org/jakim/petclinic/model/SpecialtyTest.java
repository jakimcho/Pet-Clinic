package org.jakim.petclinic.model;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpecialtyTest
        extends BaseEntityTest<Specialty>
{

    @Before
    public void setUp( )
            throws Exception
    {
        this.entity = new Specialty( );
    }

    @Test
    public void getDescription( )
    {
        String specialty = "Ortoped";
        this.entity.setDescription( specialty );

        Assertions.assertThat( entity.getDescription( ) )
                  .isEqualTo( specialty );
    }
}