package org.jakim.petclinic.services.map;

import org.jakim.petclinic.model.Vet;
import org.jakim.petclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetMapService
        extends AbstractMapService<Vet, Long>
        implements VetService
{
    @Override
    public Set<Vet> findAll( )
    {
        return super.findAll( );
    }

    @Override
    public void delete( Vet object )
    {
        super.delete( object );
    }

    @Override
    public void deleteById( Long id )
    {
        super.deleteById( id );
    }

    @Override
    public Vet findById( Long id )
    {
        return super.findById( id );
    }

    @Override
    public Vet save( Vet object )
    {
        return super.save( object.getId( ),
                           object );
    }
}