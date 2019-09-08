package org.jakim.petclinic.services.spring_data_jpa;

import org.jakim.petclinic.model.Owner;
import org.jakim.petclinic.repositories.OwnerRepository;
import org.jakim.petclinic.services.OwnerService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jakim on 8.09.19 г.
 */

@Service
@Profile( "spring_data_jpa" )
public class OwnerServiceJPA
        implements OwnerService
{
    private final OwnerRepository ownerRepository;

    public OwnerServiceJPA( OwnerRepository ownerRepository )
    {
        this.ownerRepository = ownerRepository;
        System.out.println( "OwnerServiceJPA loaded" );
    }

    @Override
    public Owner findByLastName( String lastName )
    {
        return ownerRepository.findByLastName( lastName );
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
