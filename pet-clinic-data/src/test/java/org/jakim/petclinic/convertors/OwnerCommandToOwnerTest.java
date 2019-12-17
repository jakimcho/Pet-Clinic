package org.jakim.petclinic.convertors;

import org.assertj.core.api.SoftAssertions;
import org.jakim.petclinic.commands.OwnerCommand;
import org.jakim.petclinic.model.Owner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OwnerCommandToOwnerTest
{

    private static OwnerCommandToOwner ownerCommandToOwner = new OwnerCommandToOwner( );

    @Test
    void convert( )
    {
        OwnerCommand ownerCommand = new OwnerCommand( );
        ownerCommand.setId( 1L );
        ownerCommand.setLastName( "Ivanov" );
        ownerCommand.setLastName( "Pesho" );
        ownerCommand.setCity( "Sofia" );
        ownerCommand.setAddress( "Ivan Vazov" );
        ownerCommand.setTelephone( "12321321" );

        Owner actualOwner = ownerCommandToOwner.convert( ownerCommand );

        SoftAssertions softly = new SoftAssertions( );
        softly.assertThat( actualOwner )
              .hasFieldOrPropertyWithValue( "id",
                                            ownerCommand.getId( ) );
        softly.assertThat( actualOwner )
              .hasFieldOrPropertyWithValue( "firstName",
                                            ownerCommand.getFirstName( ) );
        softly.assertThat( actualOwner )
              .hasFieldOrPropertyWithValue( "lastName",
                                            ownerCommand.getLastName( ) );
        softly.assertThat( actualOwner )
              .hasFieldOrPropertyWithValue( "city",
                                            ownerCommand.getCity( ) );
        softly.assertThat( actualOwner )
              .hasFieldOrPropertyWithValue( "address",
                                            ownerCommand.getAddress( ) );
        softly.assertThat( actualOwner )
              .hasFieldOrPropertyWithValue( "telephone",
                                            ownerCommand.getTelephone( ) );
        softly.assertThat( actualOwner )
              .hasFieldOrPropertyWithValue( "pets",
                                            ownerCommand.getPets( ) );

        softly.assertAll();

    }
}