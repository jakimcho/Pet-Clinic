package org.jakim.petclinic.services.spring_data_jpa;

import org.jakim.petclinic.model.Owner;
import org.jakim.petclinic.model.Pet;
import org.jakim.petclinic.model.Specialty;
import org.jakim.petclinic.model.Visit;
import org.jakim.petclinic.repositories.VisitRepository;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith( MockitoExtension.class )
class VisitJPAServiceTest
{
    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitJPAService visitJPAService;

    @Test
    void findAll( )
    {
        //Given
        Set<Visit> expectedVisists = getPreparedVisits( );
        when( visitRepository.findAll( ) )
                .thenReturn( expectedVisists );

        //When
        Set<Visit> actualSpecialties = visitJPAService.findAll( );

        //Then
        assertThat( actualSpecialties ).hasSize( sizeOf( expectedVisists ) );
        assertThat( actualSpecialties ).containsAll( expectedVisists );
        // Default calling times is 1
        verify( visitRepository ).findAll( );
    }

    @Test
    void findById_existingVisit( )
    {
        //Given
        Visit expectedVisit = new Visit( );
        expectedVisit.setId( 1L );
        when( visitRepository.findById( anyLong( ) ) ).thenReturn( Optional.of( expectedVisit ) );
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass( Long.class );

        //When
        Visit actualVisit = visitJPAService.findById( 1L );

        //Then
        assertThat( actualVisit ).isEqualTo( expectedVisit );
        verify( visitRepository,
                never( ) ).findAllById( anyIterable( ) );
        verify( visitRepository ).findById( argumentCaptor.capture( ) );
        assertThat( argumentCaptor.getValue( ) ).isEqualTo( 1L );
    }

    @Test
    void findById_notExistingVisit( )
    {
        //Given
        when( visitRepository.findById( anyLong( ) ) ).thenReturn( Optional.empty( ) );
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass( Long.class );

        //When
        Visit actualVisit = visitJPAService.findById( 1L );

        //Then
        assertThat( actualVisit ).isNull( );
        verify( visitRepository,
                never( ) ).findAllById( anyIterable( ) );
        verify( visitRepository ).findById( argumentCaptor.capture( ) );
        assertThat( argumentCaptor.getValue( ) ).isEqualTo( 1L );
    }

    @Test
    void save_successful( )
    {
        //Given
        Visit expectedVisit = new Visit( );
        expectedVisit.setId( 1L );
        Pet pet = new Pet( );
        pet.setId( 1L );
        Owner owner = new Owner( );
        owner.setId( 1L );
        pet.setOwner( owner );
        expectedVisit.setPet( pet );
        when( visitRepository.save( any( Visit.class ) ) ).thenReturn( expectedVisit );
        ArgumentCaptor<Visit> argumentCaptor = ArgumentCaptor.forClass( Visit.class );

        //When
        Visit actualVisit = visitJPAService.save( expectedVisit );

        //Then
        assertThat( actualVisit ).isEqualTo( expectedVisit );
        verify( visitRepository ).save( argumentCaptor.capture( ) );
        assertThat( argumentCaptor.getValue( ) ).isEqualTo( expectedVisit );
    }

    @Test
    void save_homelessPet( )
    {
        //Given
        Visit expectedVisit = new Visit( );
        expectedVisit.setId( 1L );
        Pet pet = new Pet( );
        pet.setId( 1L );
        expectedVisit.setPet( pet );

        //When
        Exception exception = assertThrows( RuntimeException.class,
                                            ( ) -> visitJPAService.save( expectedVisit ) );

        //Then
        assertThat( exception.getMessage( ) ).isEqualTo( "The pet is homeless" );
    }

    @Test
    void save_noPet( )
    {
        //Given
        Visit expectedVisit = new Visit( );
        expectedVisit.setId( 1L );
        Pet pet = new Pet( ); // Pet does not have id so it does not exist in the DB
        expectedVisit.setPet( pet );

        //When
        Exception exception = assertThrows( RuntimeException.class,
                                            ( ) -> visitJPAService.save( expectedVisit ) );

        //Then
        assertThat( exception.getMessage( ) ).isEqualTo( "No such pet in the data base" );
    }

    @Test
    void deleteById( )
    {
        //Given
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass( Long.class );

        //When
        visitJPAService.deleteById( 1L );

        //Then
        verify( visitRepository,
                never( ) ).delete( any( Visit.class ) );
        verify( visitRepository ).deleteById( argumentCaptor.capture( ) );
        assertThat( argumentCaptor.getValue( ) ).isEqualTo( 1L );
    }

    @Test
    void delete( )
    {
        //Given
        Visit expectedVisit = new Visit( );
        ArgumentCaptor<Visit> argumentCaptor = ArgumentCaptor.forClass( Visit.class );

        //When
        visitJPAService.delete( expectedVisit );

        //Then
        verify( visitRepository,
                never( ) ).deleteById( anyLong( ) );
        verify( visitRepository ).delete( argumentCaptor.capture( ) );
        assertThat( argumentCaptor.getValue( ) ).isEqualTo( expectedVisit );
    }

    private Set<Visit> getPreparedVisits( )
    {
        Set<Visit> visits = new HashSet<>( 3 );
        Visit v1 = new Visit( );
        v1.setId( 1L );

        Visit v2 = new Visit( );
        v2.setId( 2L );

        Visit v3 = new Visit( );
        v3.setId( 3L );

        visits.add( v1 );
        visits.add( v1 );
        visits.add( v1 );

        return visits;
    }
}