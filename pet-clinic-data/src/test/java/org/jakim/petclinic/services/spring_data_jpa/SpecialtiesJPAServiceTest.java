package org.jakim.petclinic.services.spring_data_jpa;

import org.jakim.petclinic.model.Specialty;
import org.jakim.petclinic.repositories.SpecialtiesRepository;
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
class SpecialtiesJPAServiceTest
{
    @Mock
    SpecialtiesRepository specialtiesRepository;

    @InjectMocks
    SpecialtiesJPAService specialtiesJPAService;

    @Test
    void findAll( )
    {
        //Given
        Set<Specialty> expectedSpecialties = getPreparedSpecialties( );
        when( specialtiesRepository.findAll( ) )
                .thenReturn( expectedSpecialties );

        //When
        Set<Specialty> actualSpecialties = specialtiesJPAService.findAll( );

        //Then
        assertThat( actualSpecialties ).hasSize( sizeOf( expectedSpecialties ) );
        assertThat( actualSpecialties ).containsAll( expectedSpecialties );
        // Default calling times is 1
        verify( specialtiesRepository ).findAll( );
    }

    @Test
    void findById_existing( )
    {
        //Given
        Specialty expectedSpecialty = new Specialty( );
        expectedSpecialty.setId( 1L );
        when( specialtiesRepository.findById( anyLong( ) ) ).thenReturn( Optional.of( expectedSpecialty ) );
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass( Long.class );

        //When
        Specialty actualSpecialty = specialtiesJPAService.findById( 1L );

        //Then
        assertThat( actualSpecialty ).isEqualTo( expectedSpecialty );
        verify( specialtiesRepository,
                never( ) ).findAllById( anyIterable( ) );
        verify( specialtiesRepository ).findById( argumentCaptor.capture( ) );
        assertThat( argumentCaptor.getValue( ) ).isEqualTo( 1L );
    }

    @Test
    void findById_notExisting( )
    {
        //Given
        when( specialtiesRepository.findById( anyLong( ) ) ).thenReturn( Optional.empty( ) );
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass( Long.class );

        //When
        Specialty actualSpecialty = specialtiesJPAService.findById( 1L );

        //Then
        assertThat( actualSpecialty ).isNull( );
        verify( specialtiesRepository,
                never( ) ).findAllById( anyIterable( ) );
        verify( specialtiesRepository ).findById( argumentCaptor.capture( ) );
        assertThat( argumentCaptor.getValue( ) ).isEqualTo( 1L );
    }

    @Test
    void save( )
    {
        //Given
        Specialty expectedSpecialty = new Specialty( );
        expectedSpecialty.setId( 1L );
        when( specialtiesRepository.save( any( Specialty.class ) ) ).thenReturn( expectedSpecialty );
        ArgumentCaptor<Specialty> argumentCaptor = ArgumentCaptor.forClass( Specialty.class );

        //When
        Specialty actualSpecialty = specialtiesJPAService.save( expectedSpecialty );

        //Then
        assertThat( actualSpecialty ).isEqualTo( expectedSpecialty );
        verify( specialtiesRepository ).save( argumentCaptor.capture( ) );
        assertThat( argumentCaptor.getValue( ) ).isEqualTo( expectedSpecialty );
    }

    @Test
    void deleteById( )
    {
        //Given
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass( Long.class );

        //When
        specialtiesJPAService.deleteById( 1L );

        //Then
        verify( specialtiesRepository,
                never( ) ).delete( any( Specialty.class ) );
        verify( specialtiesRepository ).deleteById( argumentCaptor.capture( ) );
        assertThat( argumentCaptor.getValue( ) ).isEqualTo( 1L );
    }

    @Test
    void delete( )
    {
        //Given
        Specialty expectedSpecialty = new Specialty( );
        ArgumentCaptor<Specialty> argumentCaptor = ArgumentCaptor.forClass( Specialty.class );

        //When
        specialtiesJPAService.delete( expectedSpecialty );

        //Then
        verify( specialtiesRepository,
                never( ) ).deleteById( anyLong( ) );
        verify( specialtiesRepository ).delete( argumentCaptor.capture( ) );
        assertThat( argumentCaptor.getValue( ) ).isEqualTo( expectedSpecialty );
    }

    private Set<Specialty> getPreparedSpecialties( )
    {
        Set<Specialty> specialties = new HashSet<>( 3 );
        Specialty s1 = new Specialty( );
        s1.setId( 1L );

        Specialty s2 = new Specialty( );
        s2.setId( 2L );

        Specialty s3 = new Specialty( );
        s3.setId( 4L );

        specialties.add( s1 );
        specialties.add( s2 );
        specialties.add( s3 );
        return specialties;
    }
}