package org.jakim.petclinic.services.map;

import org.jakim.petclinic.model.Specialty;
import org.jakim.petclinic.services.SpecialtiesService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile( { "default", "map" } )
public class SpecialtiesMapService
        extends AbstractMapService<Specialty, Long>
        implements SpecialtiesService
{

    public SpecialtiesMapService( )
    {
        this.LOGGER.info( "SpecialtiesMapService loaded" );
    }

    @Override
    public Set<Specialty> findAll( )
    {
        return super.findAll( );
    }

    @Override
    public void delete( Specialty object )
    {
        super.delete( object );
    }

    @Override
    public void deleteById( Long id )
    {
        super.deleteById( id );
    }

    @Override
    public Specialty save( Specialty object )
    {
        return super.save( object );
    }

    @Override
    public Specialty findById( Long id )
    {
        return super.findById( id );
    }
}
