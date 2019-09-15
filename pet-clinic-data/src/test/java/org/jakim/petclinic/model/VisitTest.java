package org.jakim.petclinic.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class VisitTest
        extends BaseEntityTest<Visit>
{

    @BeforeEach
    public void setUp( )
            throws Exception
    {
        this.entity = new Visit( );
    }

    @Test
    public void getDate( )
    {
        LocalDate date = LocalDate.now( )
                                  .plusDays( 3 );
        this.entity.setDate( date );

        Assertions.assertThat( this.entity.getDate( ) )
                  .isEqualTo( date );
    }

    @Test
    public void getDescription( )
    {
        String description = "Routine check";
        this.entity.setDescription( description );

        Assertions.assertThat( this.entity.getDescription( ) )
                  .isEqualTo( description );
    }

    @Test
    public void getPet( )
    {
        Pet thePet = new Pet( );
        thePet.setId( 1l );
        this.entity.setPet( thePet );

        Assertions.assertThat( this.entity.getPet( ) )
                  .isEqualTo( thePet );
    }
}