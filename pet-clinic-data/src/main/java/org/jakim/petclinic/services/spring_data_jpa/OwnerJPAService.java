package org.jakim.petclinic.services.spring_data_jpa;

import org.jakim.petclinic.model.Owner;
import org.jakim.petclinic.repositories.OwnerRepository;
import org.jakim.petclinic.services.OwnerService;
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
public class OwnerJPAService
        implements OwnerService
{
    private final static Logger LOGGER = LoggerFactory.getLogger( OwnerJPAService.class );
    private final OwnerRepository ownerRepository;

    public OwnerJPAService( OwnerRepository ownerRepository )
    {
        this.ownerRepository = ownerRepository;
        LOGGER.info( "OwnerServiceJPA loaded" );
    }

    @Override
    public Owner findByLastName( String lastName )
    {
        return ownerRepository.findByLastName( lastName );
    }

    @Override
    public Set<Owner> findAllByLastName( String lastName )
    {
        LOGGER.info( "Inside findAllByLastName method" );
        Set<Owner> owners = this.ownerRepository.findAllByLastNameIgnoreCase( lastName );
        LOGGER.debug( "Found {} owners.",
                      owners.size( ) );
        return owners;
    }

    @Override
    public Set<Owner> findAll( )
    {
        Set<Owner> owners = new HashSet<>( );
        ownerRepository.findAll( )
                       .iterator( )
                       .forEachRemaining( owners::add );
        return owners;
    }

    @Override
    public Owner findById( Long id )
    {
        return ownerRepository.findById( id )
                              .orElse( null );
    }

    @Override
    public Owner save( Owner owner )
    {
        return ownerRepository.save( owner );
    }

    @Override
    public void deleteById( Long id )
    {
        ownerRepository.deleteById( id );
    }

    @Override
    public void delete( Owner owner )
    {
        ownerRepository.delete( owner );
    }
}
