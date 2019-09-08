package org.jakim.petclinic.services.spring_data_jpa;

import org.jakim.petclinic.model.PetType;
import org.jakim.petclinic.repositories.PetTypeRepository;
import org.jakim.petclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jakim on 8.09.19 г.
 */
@Service
@Profile( "spring_data_jpa" )
public class PetTypeServiceJPA
        implements PetTypeService
{
    private final PetTypeRepository petTypeRepository;

    public PetTypeServiceJPA( PetTypeRepository petTypeRepository )
    {
        this.petTypeRepository = petTypeRepository;
        System.out.println( "PetTypeServiceJPA loaded" );
    }

    @Override
    public Set<PetType> findAll( )
    {
        Set<PetType> petTypes = new HashSet<>( );
        this.petTypeRepository.findAll( )
                              .iterator( )
                              .forEachRemaining( petTypes::add );
        return petTypes;
    }

    @Override
    public PetType findById( Long id )
    {
        return this.petTypeRepository.findById( id )
                                     .orElse( null );
    }

    @Override
    public PetType save( final PetType petType )
    {
        System.out.println( "saving pet type " + petType.toString( ) );
        return this.petTypeRepository.save( petType );
    }

    @Override
    public void deleteById( Long id )
    {
        this.petTypeRepository.deleteById( id );
    }

    @Override
    public void delete( final PetType petType )
    {
        this.petTypeRepository.delete( petType );
    }
}
