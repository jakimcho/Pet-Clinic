package org.jakim.petclinic.services.spring_data_jpa;

import org.jakim.petclinic.model.Pet;
import org.jakim.petclinic.model.PetType;
import org.jakim.petclinic.repositories.PetRepository;
import org.jakim.petclinic.services.PetTypeService;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith( MockitoExtension.class )
class PetJPAServiceTest
{

    @InjectMocks
    PetJPAService petJPAService;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeService petTypeService;

    @Test
    void findAll( )
    {
        //Given
        Set<Pet> expectedPets = getPreparedPets( );
        when( petRepository.findAll( ) )
                .thenReturn( expectedPets );

        //When
        Set<Pet> actualPets = petJPAService.findAll( );

        //Then
        assertThat( actualPets ).containsAll( expectedPets );
        // Default calling times is 1
        verify( petRepository ).findAll( );
    }

    @Test
    void findById_existingPet( )
    {
        //Given
        Pet expectedPet = new Pet( );
        expectedPet.setName( "Lila" );
        expectedPet.setId( 1L );
        when( petRepository.findById( anyLong( ) ) )
                .thenReturn( Optional.of( expectedPet ) );
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass( Long.class );

        //When
        Pet actualPet = petJPAService.findById( 1L );

        //Then
        assertThat( actualPet ).hasFieldOrPropertyWithValue( "id",
                                                             1L )
                               .hasFieldOrPropertyWithValue( "name",
                                                             "Lila" );
        // Default calling times is 1
        verify( petRepository ).findById( argumentCaptor.capture( ) );
        assertThat( argumentCaptor.getValue( ) ).isEqualTo( 1L );
    }

    @Test
    void findById_notExistingPet( )
    {
        //Given
        when( petRepository.findById( anyLong( ) ) )
                .thenReturn( Optional.empty( ) );
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass( Long.class );

        //When
        Pet actualPet = petJPAService.findById( 1L );

        //Then
        assertThat( actualPet ).isNull( );
        // Default calling times is 1
        verify( petRepository ).findById( argumentCaptor.capture( ) );
        assertThat( argumentCaptor.getValue( ) ).isEqualTo( 1L );
    }

    @Test
    void save_withExistingPetType( )
    {
        //Given
        PetType petType = new PetType( );
        petType.setId( 3l );
        Pet expectedPet = new Pet( );
        expectedPet.setName( "Djeik" );
        expectedPet.setId( 1L );
        expectedPet.setPetType( petType );
        ArgumentCaptor<Pet> petArgumentCaptor = ArgumentCaptor.forClass( Pet.class );
        when( petRepository.save( any( Pet.class ) ) ).thenReturn( expectedPet );

        //When
        Pet actualPet = petJPAService.save( expectedPet );

        //Then
        assertThat( actualPet ).isEqualTo( expectedPet );
        // Default calling times is 1
        verify( petRepository ).save( petArgumentCaptor.capture( ) );
        assertThat( petArgumentCaptor.getValue( ) ).isEqualTo( expectedPet );
        verify( petTypeService,
                never( ) ).save( any( PetType.class ) );
    }

    @Test
    void save_withNotExistingPetType( )
    {
        //Given
        PetType petType = new PetType( );
        PetType expectedPetType = new PetType( );
        expectedPetType.setId( 3L );
        Pet expectedPet = new Pet( );
        expectedPet.setName( "Djeik" );
        expectedPet.setId( 1L );
        expectedPet.setPetType( petType );
        ArgumentCaptor<PetType> petTypeArgumentCaptor = ArgumentCaptor.forClass( PetType.class );
        ArgumentCaptor<Pet> argumentCaptor = ArgumentCaptor.forClass( Pet.class );
        when( petRepository.save( any( Pet.class ) ) ).thenReturn( expectedPet );
        when( petTypeService.save( any( PetType.class ) ) ).thenReturn( expectedPetType );

        //When
        Pet actualPet = petJPAService.save( expectedPet );

        //Then
        assertThat( actualPet ).isEqualTo( expectedPet );
        assertThat( actualPet.getPetType( ) ).hasFieldOrPropertyWithValue( "id",
                                                                           3L );
        // Default calling times is 1
        verify( petRepository ).save( argumentCaptor.capture( ) );
        assertThat( argumentCaptor.getValue( ) ).isEqualTo( expectedPet );
        verify( petTypeService ).save( petTypeArgumentCaptor.capture( ) );
        assertThat( petTypeArgumentCaptor.getValue( ) ).hasFieldOrPropertyWithValue( "id",
                                                                                     null );
    }

    @Test
    void deleteById( )
    {
        //Given
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass( Long.class );

        //When
        petJPAService.deleteById( 1L );

        //Then

        // Default calling times is 1
        verify( petRepository ).deleteById( argumentCaptor.capture( ) );

        assertThat( argumentCaptor.getValue( ) ).isEqualTo( 1L );
    }

    @Test
    void delete( )
    {
        //Given
        Pet expectedPet = new Pet( );
        expectedPet.setName( "Mila" );
        ArgumentCaptor<Pet> argumentCaptor = ArgumentCaptor.forClass( Pet.class );

        //When
        petJPAService.delete( expectedPet );

        //Then
        // Default calling times is 1
        verify( petRepository ).delete( argumentCaptor.capture( ) );

        assertThat( argumentCaptor.getValue( ) ).hasFieldOrPropertyWithValue( "name",
                                                                              "Mila" );
    }

    private Set<Pet> getPreparedPets( )
    {
        Set<Pet> pets = new HashSet<>( 3 );
        Pet p1 = new Pet( );
        p1.setName( "Ivan" );
        p1.setId( 1L );

        Pet p2 = new Pet( );
        p2.setName( "Lila" );
        p2.setId( 2L );

        Pet p3 = new Pet( );
        p3.setName( "Amara" );
        p3.setId( 3L );

        pets.add( p1 );
        pets.add( p2 );
        pets.add( p3 );
        return pets;
    }
}