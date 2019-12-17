package org.jakim.petclinic.convertors;

import org.assertj.core.api.SoftAssertions;
import org.jakim.petclinic.commands.OwnerCommand;
import org.jakim.petclinic.model.Owner;
import org.junit.jupiter.api.Test;

class OwnerToOwnerCommandTest
{

    private static OwnerToOwnerCommand ownerToOwnerCommand = new OwnerToOwnerCommand( );

    @Test
    void convert( )
    {
        Owner owner = new Owner( );
        owner.setId( 1L );
        owner.setLastName( "Ivanov" );
        owner.setLastName( "Pesho" );
        owner.setCity( "Sofia" );
        owner.setAddress( "Ivan Vazov" );
        owner.setTelephone( "12321321" );

        OwnerCommand actualOwnerCommand = ownerToOwnerCommand.convert( owner );

        SoftAssertions softly = new SoftAssertions( );
        softly.assertThat( actualOwnerCommand )
              .hasFieldOrPropertyWithValue( "id",
                                            owner.getId( ) );
        softly.assertThat( actualOwnerCommand )
              .hasFieldOrPropertyWithValue( "firstName",
                                            owner.getFirstName( ) );
        softly.assertThat( actualOwnerCommand )
              .hasFieldOrPropertyWithValue( "lastName",
                                            owner.getLastName( ) );
        softly.assertThat( actualOwnerCommand )
              .hasFieldOrPropertyWithValue( "city",
                                            owner.getCity( ) );
        softly.assertThat( actualOwnerCommand )
              .hasFieldOrPropertyWithValue( "address",
                                            owner.getAddress( ) );
        softly.assertThat( actualOwnerCommand )
              .hasFieldOrPropertyWithValue( "telephone",
                                            owner.getTelephone( ) );
        softly.assertThat( actualOwnerCommand )
              .hasFieldOrPropertyWithValue( "pets",
                                            owner.getPets( ) );

        softly.assertAll( );

    }
}