package org.jakim.petclinic.services.spring_data_jpa;

import org.jakim.petclinic.model.PetType;
import org.jakim.petclinic.repositories.PetTypeRepository;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith( MockitoExtension.class )
class PetTypeJPAServiceTest
{

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    PetTypeJPAService petTypeJPAService;

    @Test
    void findAll( )
    {
        //Given
        Set<PetType> expectedPetTypes = getPreparedPetTypes( );
        when( petTypeRepository.findAll( ) ).thenReturn( expectedPetTypes );

        //When
        Set<PetType> actualPetTypes = this.petTypeJPAService.findAll( );

        //Then
        assertThat( actualPetTypes ).containsAll( expectedPetTypes );
        verify( petTypeRepository ).findAll( );
    }

    @Test
    void findById_found( )
    {
        //Given
        PetType expectedPetType = new PetType( );
        when( petTypeRepository.findById( anyLong( ) ) ).thenReturn( Optional.of( expectedPetType ) );
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass( Long.class );

        //When
        PetType actualPetType = this.petTypeJPAService.findById( 1L );

        //Then
        assertThat( actualPetType ).isEqualTo( expectedPetType );
        verify( petTypeRepository,
                never( ) ).findAll( );
        verify( petTypeRepository ).findById( argumentCaptor.capture( ) );
        assertThat( argumentCaptor.getValue( ) ).isEqualTo( 1L );
    }

    @Test
    void findById_notFount( )
    {
        //Given
        when( petTypeRepository.findById( anyLong( ) ) ).thenReturn( Optional.empty( ) );
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass( Long.class );

        //When
        PetType actualPetType = this.petTypeJPAService.findById( 1L );

        //Then
        assertThat( actualPetType ).isNull( );
        verify( petTypeRepository,
                never( ) ).findAll( );
        verify( petTypeRepository ).findById( argumentCaptor.capture( ) );
        assertThat( argumentCaptor.getValue( ) ).isEqualTo( 1L );
    }

    @Test
    void save( )
    {
        //Given
        PetType expectedPetType = new PetType( );
        expectedPetType.setId( 1L );
        when( petTypeRepository.save( any( PetType.class ) ) ).thenReturn( expectedPetType );
        ArgumentCaptor<PetType> argumentCaptor = ArgumentCaptor.forClass( PetType.class );

        //When
        PetType actualPetType = petTypeJPAService.save( expectedPetType );

        //Then
        assertThat( actualPetType ).isEqualTo( expectedPetType );
        verify( petTypeRepository ).save( argumentCaptor.capture( ) );
        assertThat( argumentCaptor.getValue( ) ).isEqualTo( expectedPetType );
    }

    @Test
    void deleteById( )
    {
        //Given
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass( Long.class );

        //When
        this.petTypeJPAService.deleteById( 1L );

        //Then
        verify( petTypeRepository,
                never( ) ).deleteAll( );
        verify( petTypeRepository,
                never( ) ).delete( any( PetType.class ) );
        verify( petTypeRepository ).deleteById( argumentCaptor.capture( ) );
        assertThat( argumentCaptor.getValue( ) ).isEqualTo( 1L );
    }

    @Test
    void delete( )
    {
        //Given
        PetType expectedPetType = new PetType( );
        expectedPetType.setId( 1L );
        ArgumentCaptor<PetType> argumentCaptor = ArgumentCaptor.forClass( PetType.class );

        //When
        petTypeJPAService.delete( expectedPetType );

        //Then
        verify( petTypeRepository,
                never( ) ).deleteAll( );
        verify( petTypeRepository,
                never( ) ).deleteById( anyLong( ) );
        verify( petTypeRepository ).delete( argumentCaptor.capture( ) );
        assertThat( argumentCaptor.getValue( ) ).isEqualTo( expectedPetType );
    }

    private Set<PetType> getPreparedPetTypes( )
    {
        Set<PetType> petTypes = new HashSet<>( 3 );
        PetType pt1 = new PetType( );
        pt1.setId( 1l );
        pt1.setName( "Parrot" );

        PetType pt2 = new PetType( );
        pt2.setId( 2l );
        pt2.setName( "Cat" );

        PetType pt3 = new PetType( );
        pt3.setId( 3l );
        pt3.setName( "Dog" );

        petTypes.add( pt1 );
        petTypes.add( pt2 );
        petTypes.add( pt3 );
        return petTypes;
    }
}