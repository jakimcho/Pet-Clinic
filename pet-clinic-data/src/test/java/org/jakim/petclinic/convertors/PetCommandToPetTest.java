package org.jakim.petclinic.convertors;

import org.assertj.core.api.SoftAssertions;
import org.jakim.petclinic.commands.PetCommand;
import org.jakim.petclinic.model.Owner;
import org.jakim.petclinic.model.Pet;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

import static org.assertj.core.api.Java6Assertions.assertThat;


class PetCommandToPetTest
{

    private static PetCommandToPet petCommandToPet = new PetCommandToPet( );

    @Test
    void convert( )
    {
        Owner owner = new Owner( );
        owner.setLastName( "Ivanov" );

        PetCommand petCommand = new PetCommand( );
        petCommand.setId( 1l );
        petCommand.setName( "Test" );
        petCommand.setOwner( owner );
        petCommand.setVisits( null );
        petCommand.setBirthDate( LocalDate.now( ) );

        Pet actualPet = petCommandToPet.convert( petCommand );

        SoftAssertions softly = new SoftAssertions( );
        softly.assertThat( actualPet )
              .hasFieldOrPropertyWithValue( "id",
                                            petCommand.getId( ) );
        softly.assertThat( actualPet )
              .hasFieldOrPropertyWithValue( "name",
                                            petCommand.getName( ) );
        softly.assertThat( actualPet )
              .hasFieldOrPropertyWithValue( "owner",
                                            petCommand.getOwner( ) );
        softly.assertThat( actualPet )
              .hasFieldOrPropertyWithValue( "birthDate",
                                            petCommand.getBirthDate( ) );
        softly.assertThat( actualPet )
              .hasFieldOrPropertyWithValue( "visits",
                                            petCommand.getVisits( ) );
        softly.assertAll( );
    }
}