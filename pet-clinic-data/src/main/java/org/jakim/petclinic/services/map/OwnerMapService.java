package org.jakim.petclinic.services.map;

import org.jakim.petclinic.model.Owner;
import org.jakim.petclinic.model.Pet;
import org.jakim.petclinic.model.PetType;
import org.jakim.petclinic.services.OwnerService;
import org.jakim.petclinic.services.PetService;
import org.jakim.petclinic.services.PetTypeService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile( { "default", "map" } )
public class OwnerMapService
        extends AbstractMapService<Owner, Long>
        implements OwnerService
{
    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerMapService( PetTypeService petTypeService,
                            PetService petService )
    {
        this.petTypeService = petTypeService;
        this.petService = petService;
        this.LOGGER.info( "OwnerMapService loaded" );
    }

    @Override
    public Set<Owner> findAll( )
    {
        return super.findAll( );
    }

    @Override
    public void delete( Owner object )
    {
        super.delete( object );
    }

    @Override
    public void deleteById( Long id )
    {
        super.deleteById( id );
    }

    @Override
    public Owner findById( Long id )
    {
        return super.findById( id );
    }

    @Override
    public Owner save( Owner object )
    {
        if( object == null )
        {
            return null;
        }

        object.getPets( )
              .forEach( this::persistPet );

        return super.save( object );
    }

    @Override
    public Owner findByLastName( String lastName )
    {
        return null;
    }

    private void persistPet( final Pet pet )
    {
        PersistPetType( pet.getPetType( ) );
        if( pet.getId( ) == null )
        {
            petService.save( pet );
        }
    }

    private void PersistPetType( final PetType petType )
    {
        if( petType == null )
        {
            throw new RuntimeException( "PetType is required" );
        }

        if( petType.getId( ) == null )
        {
            petTypeService.save( petType );
        }
    }
}
