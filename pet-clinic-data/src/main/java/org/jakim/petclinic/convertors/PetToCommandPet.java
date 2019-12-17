package org.jakim.petclinic.convertors;

import lombok.Synchronized;
import org.jakim.petclinic.commands.PetCommand;
import org.jakim.petclinic.model.Pet;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by jakim on 17.12.19 г.
 */

@Component
public class PetToCommandPet
        implements Converter<Pet, PetCommand>
{
    @Synchronized
    @Override
    public PetCommand convert( Pet pet )
    {
        if( pet == null )
        {
            return null;
        }

        final PetCommand petCommand = new PetCommand( );
        petCommand.setName( pet.getName( ) );
        petCommand.setOwner( pet.getOwner( ) );
        petCommand.setPetType( pet.getPetType( ) );
        petCommand.setBirthDate( pet.getBirthDate( ) );
        petCommand.setVisits( pet.getVisits( ) );
        petCommand.setId( pet.getId( ) );
        return petCommand;
    }
}
