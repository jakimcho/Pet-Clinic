package org.jakim.petclinic.services.spring_data_jpa;

import org.jakim.petclinic.model.PetType;
import org.jakim.petclinic.repositories.PetTypeRepository;
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
public class PetTypeJPAService
        implements PetTypeService
{
    private final static Logger LOGGER = LoggerFactory.getLogger( PetTypeJPAService.class );
    private final PetTypeRepository petTypeRepository;

    public PetTypeJPAService( PetTypeRepository petTypeRepository )
    {
        this.petTypeRepository = petTypeRepository;
        LOGGER.info( "PetTypeServiceJPA loaded" );
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
        LOGGER.info( "saving pet type {}",
                     petType );
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
