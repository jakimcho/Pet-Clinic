package org.jakim.petclinic.formaters;

import org.jakim.petclinic.model.PetType;
import org.jakim.petclinic.services.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

/**
 * Created by jakim on 3.11.19 г.
 */
@Component
public class PetTypeFormatter
        implements Formatter<PetType>
{
    private final PetTypeService petTypeService;


    @Autowired
    public PetTypeFormatter( PetTypeService petTypeService )
    {
        this.petTypeService = petTypeService;
    }

    @Override
    public String print( PetType petType,
                         Locale locale )
    {
        return petType.getName( );
    }

    @Override
    public PetType parse( String text,
                          Locale locale )
            throws ParseException
    {
        Collection<PetType> findPetTypes = this.petTypeService.findAll( );
        for( PetType type : findPetTypes )
        {
            if( type.getName( )
                    .equals( text ) )
            {
                return type;
            }
        }

        throw new ParseException( "type not found: " + text,
                                  0 );
    }
}
