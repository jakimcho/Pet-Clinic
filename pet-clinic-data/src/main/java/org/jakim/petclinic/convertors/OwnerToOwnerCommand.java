package org.jakim.petclinic.convertors;

import lombok.Synchronized;
import org.jakim.petclinic.commands.OwnerCommand;
import org.jakim.petclinic.model.Owner;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by jakim on 17.12.19 г.
 */

@Component
public class OwnerToOwnerCommand
        implements Converter<Owner, OwnerCommand>
{
    @Synchronized
    @Override
    public OwnerCommand convert( Owner owner )
    {
        if( owner == null )
        {
            return null;
        }

        OwnerCommand ownerCommand = new OwnerCommand( );
        ownerCommand.setId( owner.getId( ) );
        ownerCommand.setFirstName( owner.getFirstName( ) );
        ownerCommand.setLastName( owner.getLastName( ) );
        ownerCommand.setAddress( owner.getAddress( ) );
        ownerCommand.setCity( owner.getCity( ) );
        ownerCommand.setPets( owner.getPets( ) );
        ownerCommand.setTelephone( owner.getTelephone( ) );

        return ownerCommand;
    }
}
