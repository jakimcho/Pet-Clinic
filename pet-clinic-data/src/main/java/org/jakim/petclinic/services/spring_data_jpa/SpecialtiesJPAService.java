package org.jakim.petclinic.services.spring_data_jpa;

import org.jakim.petclinic.model.Specialty;
import org.jakim.petclinic.repositories.SpecialtiesRepository;
import org.jakim.petclinic.services.SpecialtiesService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jakim on 8.09.19 г.
 */
@Service
@Profile( "spring_data_jpa" )
public class SpecialtiesJPAService
        implements SpecialtiesService
{
    private final SpecialtiesRepository specialtiesRepository;

    public SpecialtiesJPAService( SpecialtiesRepository specialtiesRepository )
    {
        this.specialtiesRepository = specialtiesRepository;
        System.out.println( "SpecialtiesServiceJPA loaded" );
    }

    @Override
    public Set<Specialty> findAll( )
    {
        Set<Specialty> specialties = new HashSet<>( );
        this.specialtiesRepository.findAll( )
                                  .iterator( )
                                  .forEachRemaining( specialties::add );
        return specialties;
    }

    @Override
    public Specialty findById( Long id )
    {
        return this.specialtiesRepository.findById( id )
                                         .orElse( null );
    }

    @Override
    public Specialty save( final Specialty specialty )
    {
        return this.specialtiesRepository.save( specialty );
    }

    @Override
    public void deleteById( Long id )
    {
        this.specialtiesRepository.deleteById( id );
    }

    @Override
    public void delete( final Specialty specialty )
    {
        this.specialtiesRepository.delete( specialty );
    }
}
