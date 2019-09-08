package org.jakim.petclinic.services.map;

import org.jakim.petclinic.model.Pet;
import org.jakim.petclinic.model.Visit;
import org.jakim.petclinic.services.VisitService;
import org.jakim.petclinic.services.spring_data_jpa.VisitJPAService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by jakim on 8.09.19 г.
 */
@Service
@Profile( { "default", "map" } )
public class VisitMapService
        extends AbstractMapService<Visit, Long>
        implements VisitService
{
    public VisitMapService( )
    {
        System.out.println( "VisitMapService loaded" );
    }

    @Override
    public Set<Visit> findAll( )
    {
        return super.findAll( );
    }

    @Override
    public void delete( final Visit visit )
    {
        super.delete( visit );
    }

    @Override
    public void deleteById( Long id )
    {
        super.deleteById( id );
    }

    @Override
    public Visit save( final Visit visit )
    {
        VisitJPAService.verifyVisitPet( visit );
        return super.save( visit );
    }

    @Override
    public Visit findById( Long id )
    {
        return super.findById( id );
    }
}
