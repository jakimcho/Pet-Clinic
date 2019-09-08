package org.jakim.petclinic.services.map;

import org.jakim.petclinic.model.Pet;
import org.jakim.petclinic.model.Visit;
import org.jakim.petclinic.services.VisitService;
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
        Pet pet = visit.getPet( );
        if( pet == null || pet.getId( ) == null )
        {
            throw new RuntimeException( "No such pet in the data base" );
        }

        if( pet.getOwner( ) == null || pet.getOwner( )
                                          .getId( ) == null )
        {
            throw new RuntimeException( "The pet is homeless" );
        }


        return super.save( visit );
    }

    @Override
    public Visit findById( Long id )
    {
        return super.findById( id );
    }
}
