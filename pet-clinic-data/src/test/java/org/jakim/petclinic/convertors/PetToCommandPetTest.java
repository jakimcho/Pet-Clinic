package org.jakim.petclinic.convertors;

import org.assertj.core.api.SoftAssertions;
import org.jakim.petclinic.commands.PetCommand;
import org.jakim.petclinic.model.Owner;
import org.jakim.petclinic.model.Pet;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class PetToCommandPetTest
{

    private static PetToCommandPet petToPetCommand = new PetToCommandPet( );

    @Test
    void convert( )
    {
        Owner owner = new Owner( );
        owner.setLastName( "Ivanov" );

        Pet pet = new Pet( );
        pet.setId( 1l );
        pet.setName( "Test" );
        pet.setOwner( owner );
        pet.setVisits( null );
        pet.setBirthDate( LocalDate.now( ) );

        PetCommand actualPetCommand = petToPetCommand.convert( pet );

        SoftAssertions softly = new SoftAssertions( );
        softly.assertThat( actualPetCommand )
              .hasFieldOrPropertyWithValue( "id",
                                            pet.getId( ) );
        softly.assertThat( actualPetCommand )
              .hasFieldOrPropertyWithValue( "name",
                                            pet.getName( ) );
        softly.assertThat( actualPetCommand )
              .hasFieldOrPropertyWithValue( "owner",
                                            pet.getOwner( ) );
        softly.assertThat( actualPetCommand )
              .hasFieldOrPropertyWithValue( "birthDate",
                                            pet.getBirthDate( ) );
        softly.assertThat( actualPetCommand )
              .hasFieldOrPropertyWithValue( "visits",
                                            pet.getVisits( ) );
        softly.assertAll( );
    }
}