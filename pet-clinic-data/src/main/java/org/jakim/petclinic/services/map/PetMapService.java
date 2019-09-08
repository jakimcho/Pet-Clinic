package org.jakim.petclinic.services.map;

import org.jakim.petclinic.model.Pet;
import org.jakim.petclinic.services.PetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile( { "default", "map"} )
public class PetMapService
        extends AbstractMapService<Pet, Long>
        implements PetService
{
    public PetMapService( )
    {
        System.out.println("PetMapService loaded" );
    }

    @Override
    public Set<Pet> findAll( )
    {
        return super.findAll( );
    }

    @Override
    public void delete( Pet object )
    {
        super.delete( object );
    }

    @Override
    public void deleteById( Long id )
    {
        super.deleteById( id );
    }

    @Override
    public Pet findById( Long id )
    {
        return super.findById( id );
    }

    @Override
    public Pet save( Pet object )
    {
        return super.save( object );
    }
}
