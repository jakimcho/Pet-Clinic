package org.jakim.petclinic.services.spring_data_jpa;

import org.jakim.petclinic.model.Specialty;
import org.jakim.petclinic.model.Vet;
import org.jakim.petclinic.repositories.VetRepository;
import org.jakim.petclinic.services.SpecialtiesService;
import org.jakim.petclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jakim on 8.09.19 г.
 */
@Service
@Profile( "spring_data_jpa" )
public class VetJPAService
        implements VetService
{
    private final VetRepository vetRepository;
    private final SpecialtiesService specialtiesService;

    public VetJPAService( VetRepository vetRepository,
                          SpecialtiesService specialtiesService )
    {
        this.vetRepository = vetRepository;
        this.specialtiesService = specialtiesService;
        System.out.println( "VetServiceJPA loaded" );
    }

    @Override
    public Set<Vet> findAll( )
    {
        Set<Vet> vets = new HashSet<>( );
        this.vetRepository.findAll( )
                          .iterator( )
                          .forEachRemaining( vets::add );
        return vets;
    }

    @Override
    public Vet findById( Long id )
    {
        return this.vetRepository.findById( id )
                                 .orElse( null );
    }

    @Override
    public Vet save( final Vet vet )
    {
        for( Specialty specialty : vet.getSpecialties( )
        )
        {
            if( specialty.getId( ) == null )
            {
                specialtiesService.save( specialty );
            }
        }

        return this.vetRepository.save( vet );
    }

    @Override
    public void deleteById( Long id )
    {
        this.vetRepository.deleteById( id );
    }

    @Override
    public void delete( Vet vet )
    {
        this.vetRepository.delete( vet );
    }
}
