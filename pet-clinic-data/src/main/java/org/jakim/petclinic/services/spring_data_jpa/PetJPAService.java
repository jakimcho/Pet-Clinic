package org.jakim.petclinic.services.spring_data_jpa;

import org.jakim.petclinic.model.Pet;
import org.jakim.petclinic.model.PetType;
import org.jakim.petclinic.repositories.PetRepository;
import org.jakim.petclinic.services.PetService;
import org.jakim.petclinic.services.PetTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jakim on 8.09.19 г.
 */

@Service
@Profile( "spring_data_jpa" )
public class PetJPAService
        implements PetService
{
    private final static Logger LOGGER = LoggerFactory.getLogger( PetJPAService.class );
    private final PetRepository petRepository;

    private final PetTypeService petTypeService;

    public PetJPAService( PetRepository petRepository,
                          PetTypeService petTypeService )
    {
        this.petRepository = petRepository;
        this.petTypeService = petTypeService;
        LOGGER.info( "PetServiceJPA loaded" );
    }

    @Override
    public Set<Pet> findAll( )
    {
        Set<Pet> pets = new HashSet<>( );
        this.petRepository.findAll( )
                          .iterator( )
                          .forEachRemaining( pets::add );
        return pets;
    }

    @Override
    public Pet findById( Long id )
    {
        return this.petRepository.findById( id )
                                 .orElse( null );
    }

    @Override
    public Pet save( final Pet pet )
    {
        PetType petType = pet.getPetType( );
        LOGGER.info( "Trying to save Pet of type {}",
                     petType.toString( ) );
        if( petType.getId( ) == null )
        {
            pet.setPetType( petTypeService.save( petType ) );
        }

        return this.petRepository.save( pet );
    }

    @Override
    public void deleteById( Long id )
    {
        this.petRepository.deleteById( id );
    }

    @Override
    public void delete( final Pet pet )
    {
        this.petRepository.delete( pet );
    }

}
