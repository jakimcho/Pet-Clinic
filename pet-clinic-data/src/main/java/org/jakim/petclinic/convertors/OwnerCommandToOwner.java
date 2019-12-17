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
public class OwnerCommandToOwner
        implements Converter<OwnerCommand, Owner>
{
    @Synchronized
    @Override
    public Owner convert( OwnerCommand ownerCommand )
    {
        if( ownerCommand == null )
        {
            return null;
        }

        Owner owner = new Owner( );
        owner.setId( ownerCommand.getId( ) );
        owner.setFirstName( ownerCommand.getFirstName( ) );
        owner.setLastName( ownerCommand.getLastName( ) );
        owner.setAddress( ownerCommand.getAddress( ) );
        owner.setCity( ownerCommand.getCity( ) );
        owner.setPets( ownerCommand.getPets( ) );
        owner.setTelephone( ownerCommand.getTelephone( ) );

        return owner;
    }
}
