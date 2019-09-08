package org.jakim.petclinic.services.spring_data_jpa;

import org.jakim.petclinic.model.Pet;
import org.jakim.petclinic.model.Visit;
import org.jakim.petclinic.repositories.VisitRepository;
import org.jakim.petclinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jakim on 8.09.19 г.
 */

@Service
@Profile( "spring_data_jpa" )
public class VisitJPAService
        implements VisitService
{
    private final VisitRepository visitRepository;

    public VisitJPAService( VisitRepository visitRepository )
    {
        this.visitRepository = visitRepository;
        System.out.println( "VetServiceJPA loaded" );
    }

    @Override
    public Set<Visit> findAll( )
    {
        Set<Visit> visits = new HashSet<>( );
        this.visitRepository.findAll( )
                            .iterator( )
                            .forEachRemaining( visits::add );
        return visits;
    }

    @Override
    public Visit findById( Long id )
    {
        return this.visitRepository.findById( id )
                                   .orElse( null );
    }

    @Override
    public Visit save( final Visit visit )
    {
        verifyVisitPet( visit );
        return this.visitRepository.save( visit );
    }

    @Override
    public void deleteById( Long aLong )
    {

    }

    @Override
    public void delete( Visit object )
    {

    }

    public static void verifyVisitPet( Visit visit )
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
    }
}
