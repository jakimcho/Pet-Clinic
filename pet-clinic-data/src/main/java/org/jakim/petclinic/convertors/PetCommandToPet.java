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
public class PetCommandToPet
        implements Converter<PetCommand, Pet>
{

    @Synchronized
    @Override
    public Pet convert( PetCommand petCommand )
    {
        if( petCommand == null )
        {
            return null;
        }

        final Pet pet = new Pet( );
        pet.setId( petCommand.getId( ) );
        pet.setName( petCommand.getName( ) );
        pet.setOwner( petCommand.getOwner( ) );
        pet.setPetType( petCommand.getPetType( ) );
        pet.setBirthDate( petCommand.getBirthDate( ) );
        pet.setVisits( petCommand.getVisits( ) );
        return pet;
    }
}
