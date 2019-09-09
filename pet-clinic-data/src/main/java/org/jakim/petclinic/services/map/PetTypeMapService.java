package org.jakim.petclinic.services.map;

import org.jakim.petclinic.model.PetType;
import org.jakim.petclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile( { "default", "map" } )
public class PetTypeMapService
        extends AbstractMapService<PetType, Long>
        implements PetTypeService
{
    public PetTypeMapService( )
    {
        this.LOGGER.info( "PetTypeMapService loaded" );
    }

    @Override
    public Set<PetType> findAll( )
    {
        return super.findAll( );
    }

    @Override
    public void delete( PetType object )
    {
        super.delete( object );
    }

    @Override
    public void deleteById( Long id )
    {
        super.deleteById( id );
    }

    @Override
    public PetType findById( Long id )
    {
        return super.findById( id );
    }

    @Override
    public PetType save( PetType object )
    {
        return super.save( object );
    }
}
