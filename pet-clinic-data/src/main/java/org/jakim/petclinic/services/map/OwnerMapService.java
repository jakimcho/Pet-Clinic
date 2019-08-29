package org.jakim.petclinic.services.map;

import org.jakim.petclinic.model.Owner;
import org.jakim.petclinic.services.OwnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OwnerMapService
        extends AbstractMapService<Owner, Long>
        implements OwnerService
{
    @Override
    public Set<Owner> findAll( )
    {
        return super.findAll( );
    }

    @Override
    public void delete( Owner object )
    {
        super.delete( object );
    }

    @Override
    public void deleteById( Long id )
    {
        super.deleteById( id );
    }

    @Override
    public Owner findById( Long id )
    {
        return super.findById( id );
    }

    @Override
    public Owner save( Owner object )
    {
        return super.save( object );
    }

    @Override
    public Owner findByLastName( String lastName )
    {
        return null;
    }
}
