package org.jakim.petclinic.services.map;

import org.jakim.petclinic.model.Specialty;
import org.jakim.petclinic.model.Vet;
import org.jakim.petclinic.services.SpecialtiesService;
import org.jakim.petclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile( { "default", "map" } )
public class VetMapService
        extends AbstractMapService<Vet, Long>
        implements VetService
{
    private final SpecialtiesService specialtiesService;

    public VetMapService( SpecialtiesService specialtiesService )
    {
        this.specialtiesService = specialtiesService;
        this.LOGGER.info( "VetMapService loaded" );
    }

    @Override
    public Set<Vet> findAll( )
    {
        return super.findAll( );
    }

    @Override
    public void delete( Vet object )
    {
        super.delete( object );
    }

    @Override
    public void deleteById( Long id )
    {
        super.deleteById( id );
    }

    @Override
    public Vet findById( Long id )
    {
        return super.findById( id );
    }

    @Override
    public Vet save( Vet object )
    {
        if( object == null )
        {
            throw new RuntimeException( "Vet is null" );
        }

        if( object.getSpecialties( ) == null )
        {
            throw new RuntimeException( "What vet has no idea of vet specialties" );
        }

        object.getSpecialties( )
              .forEach( this::persistSpecialty );
        return super.save( object );
    }

    private void persistSpecialty( Specialty specialty )
    {
        if( specialty == null )
        {
            throw new RuntimeException( "Specialty cannot be null" );
        }

        if( specialty.getId( ) == null )
        {
            specialtiesService.save( specialty );
        }
    }
}
