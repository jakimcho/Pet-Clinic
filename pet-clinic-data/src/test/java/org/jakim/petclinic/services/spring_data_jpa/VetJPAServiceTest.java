package org.jakim.petclinic.services.spring_data_jpa;

import org.jakim.petclinic.model.Specialty;
import org.jakim.petclinic.model.Vet;
import org.jakim.petclinic.repositories.VetRepository;
import org.jakim.petclinic.services.SpecialtiesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.IterableUtil.sizeOf;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith( MockitoExtension.class )
class VetJPAServiceTest
{

    @Mock
    VetRepository vetRepository;

    @Mock
    SpecialtiesService specialtiesService;

    @InjectMocks
    VetJPAService vetJPAService;

    @Test
    void findAll( )
    {
        //Given
        Set<Vet> expectedVets = getPreparedVets( );
        when( vetRepository.findAll( ) )
                .thenReturn( expectedVets );

        //When
        Set<Vet> actualVets = vetJPAService.findAll( );

        //Then
        assertThat( actualVets ).hasSize( sizeOf( expectedVets ) );
        assertThat( actualVets ).containsAll( expectedVets );
        // Default calling times is 1
        verify( vetRepository ).findAll( );
    }

    @Test
    void findById_existingVet( )
    {
        //Given
        Vet expectedVet = new Vet( );
        expectedVet.setId( 1L );
        expectedVet.setFirstName( "Ivan" );
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass( Long.class );
        when( vetRepository.findById( anyLong( ) ) )
                .thenReturn( Optional.of( expectedVet ) );

        //When
        Vet actualVet = vetJPAService.findById( 1l );

        //Then
        assertThat( actualVet ).isEqualTo( expectedVet );
        // Default calling times is 1
        verify( vetRepository,
                never( ) ).findAll( );
        verify( vetRepository ).findById( argumentCaptor.capture( ) );
        assertThat( argumentCaptor.getValue( ) ).isEqualTo( 1l );
    }

    @Test
    void findById_notExistingVet( )
    {
        //Given
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass( Long.class );
        when( vetRepository.findById( anyLong( ) ) )
                .thenReturn( Optional.empty( ) );

        //When
        Vet actualVet = vetJPAService.findById( 1l );

        //Then
        assertThat( actualVet ).isNull( );
        // Default calling times is 1
        verify( vetRepository,
                never( ) ).findAll( );
        verify( vetRepository ).findById( argumentCaptor.capture( ) );
        assertThat( argumentCaptor.getValue( ) ).isEqualTo( 1l );
    }

    @Test
    void save_noSpecialties( )
    {
        //Given
        Vet expectedVet = new Vet( );
        expectedVet.setId( 1L );
        expectedVet.setFirstName( "Ivan" );
        when( vetRepository.save( any( Vet.class ) ) ).thenReturn( expectedVet );
        ArgumentCaptor<Vet> argumentCaptor = ArgumentCaptor.forClass( Vet.class );

        //When
        Vet actualVet = vetJPAService.save( expectedVet );

        //Then
        assertThat( actualVet ).isEqualTo( expectedVet );
        verify( vetRepository ).save( argumentCaptor.capture( ) );
        verify( specialtiesService,
                never( ) ).save( any( Specialty.class ) );
        assertThat( argumentCaptor.getValue( ) ).isEqualTo( expectedVet );
    }

    @Test
    void save_notSavedSpecialties( )
    {
        //Given
        Vet expectedVet = new Vet( );
        expectedVet.setId( 1L );
        expectedVet.setFirstName( "Ivan" );
        Specialty expectedSpecialty = new Specialty( );
        expectedVet.addSpecialty( expectedSpecialty );
        when( vetRepository.save( any( Vet.class ) ) ).thenReturn( expectedVet );
        ArgumentCaptor<Vet> argumentCaptor = ArgumentCaptor.forClass( Vet.class );
        ArgumentCaptor<Specialty> specialtyArgumentCaptor = ArgumentCaptor.forClass( Specialty.class );

        //When
        Vet actualVet = vetJPAService.save( expectedVet );

        //Then
        assertThat( actualVet ).isEqualTo( expectedVet );
        verify( vetRepository ).save( argumentCaptor.capture( ) );
        verify( specialtiesService ).save( specialtyArgumentCaptor.capture( ) );
        assertThat( argumentCaptor.getValue( ) ).isEqualTo( expectedVet );
        assertThat( specialtyArgumentCaptor.getValue( ) ).isEqualTo( expectedSpecialty );
    }

    @Test
    void save_withSavedSpecialties( )
    {
        //Given
        Vet expectedVet = new Vet( );
        expectedVet.setId( 1L );
        expectedVet.setFirstName( "Ivan" );
        Specialty expectedSpecialty = new Specialty( );
        expectedSpecialty.setId( 1L );
        expectedVet.addSpecialty( expectedSpecialty );
        when( vetRepository.save( any( Vet.class ) ) ).thenReturn( expectedVet );
        ArgumentCaptor<Vet> argumentCaptor = ArgumentCaptor.forClass( Vet.class );

        //When
        Vet actualVet = vetJPAService.save( expectedVet );

        //Then
        assertThat( actualVet ).isEqualTo( expectedVet );
        verify( vetRepository ).save( argumentCaptor.capture( ) );
        verify( specialtiesService, never() ).save( any( Specialty.class ));
        assertThat( argumentCaptor.getValue( ) ).isEqualTo( expectedVet );
    }

    @Test
    void deleteById( )
    {
        //Given
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass( Long.class );

        //When
        vetJPAService.deleteById( 1L );

        //Then
        verify( vetRepository,
                never( ) ).delete( any( Vet.class ) );
        verify( vetRepository ).deleteById( argumentCaptor.capture( ) );
        assertThat( argumentCaptor.getValue( ) ).isEqualTo( 1L );
    }

    @Test
    void delete( )
    {
        //Given
        Vet expectedVet = new Vet( );
        expectedVet.setId( 1L );
        expectedVet.setFirstName( "Ivan" );
        ArgumentCaptor<Vet> argumentCaptor = ArgumentCaptor.forClass( Vet.class );

        //When
        vetJPAService.delete( expectedVet );

        //Then
        verify( vetRepository,
                never( ) ).deleteById( anyLong( ) );
        verify( vetRepository ).delete( argumentCaptor.capture( ) );
        assertThat( argumentCaptor.getValue( ) ).isEqualTo( expectedVet );
    }

    private Set<Vet> getPreparedVets( )
    {
        Set<Vet> vets = new HashSet<>( 3 );
        Vet v1 = new Vet( );
        v1.setId( 1L );
        v1.setFirstName( "Ivan" );

        Vet v2 = new Vet( );
        v2.setId( 2L );
        v2.setFirstName( "Dimityr" );

        Vet v3 = new Vet( );
        v3.setId( 3L );
        v3.setFirstName( "Mila" );

        vets.add( v1 );
        vets.add( v2 );
        vets.add( v3 );
        return vets;
    }
}