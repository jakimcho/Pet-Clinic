package org.jakim.petclinic.services.map;

import org.jakim.petclinic.model.Owner;
import org.jakim.petclinic.model.Pet;
import org.jakim.petclinic.model.PetType;
import org.jakim.petclinic.services.PetService;
import org.jakim.petclinic.services.PetTypeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class OwnerMapServiceTest
{

    private OwnerMapService ownerMapService;

    @Mock
    private PetTypeService petTypeService;
    @Mock
    private PetService petService;

    @Before
    public void setUp( )
            throws Exception
    {
        MockitoAnnotations.initMocks( this );
        this.ownerMapService = new OwnerMapService( petTypeService,
                                                    petService );
    }

    @Test
    public void findAllOwnersTest( )
    {
        // Given
        List<Owner> owners = prepareOwners( );
        owners.forEach( owner -> ownerMapService.save( owner ) );

        // When
        Set<Owner> foundOwners = ownerMapService.findAll( );

        // Then
        assertThat( foundOwners ).containsExactlyInAnyOrderElementsOf( owners );
    }

    @Test
    public void deleteOwnerByIdTest( )
    {
        // Given
        List<Owner> owners = prepareOwners( );
        owners.forEach( owner -> ownerMapService.save( owner ) );

        // When
        ownerMapService.deleteById( owners.get( 1 )
                                          .getId( ) );
        // Then
        Set<Owner> foundOwners = ownerMapService.findAll( );
        assertThat( foundOwners ).hasSize( 1 );
        assertThat( foundOwners ).containsOnly( owners.get( 0 ) );
    }

    @Test
    public void deleteOwnerTest( )
    {
        // Given
        List<Owner> owners = prepareOwners( );
        owners.forEach( owner -> ownerMapService.save( owner ) );

        // When
        ownerMapService.delete( owners.get( 1 ) );

        // Then
        Set<Owner> foundOwners = ownerMapService.findAll( );
        assertThat( foundOwners ).hasSize( 1 );
        assertThat( foundOwners ).containsOnly( owners.get( 0 ) );
    }

    @Test
    public void findOwnerByIdTest( )
    {
        // Given
        List<Owner> owners = prepareOwners( );
        owners.forEach( owner -> ownerMapService.save( owner ) );

        // When
        Owner owner = ownerMapService.findById( owners.get( 1 )
                                                      .getId( ) );

        // Then
        assertThat( owner ).isEqualTo( owners.get( 1 ) );
    }

    @Test
    public void findOwnerByNotExistingIdTest( )
    {
        // Given
        List<Owner> owners = prepareOwners( );
        owners.forEach( owner -> ownerMapService.save( owner ) );

        // When
        Owner owner = ownerMapService.findById( 3L );

        // Then
        assertThat( owner ).isNull( );
    }

    @Test
    public void findOwnerByLastNameIdTest( )
    {
        // Given
        List<Owner> owners = prepareOwners( );
        owners.forEach( owner -> ownerMapService.save( owner ) );

        // When
        Owner owner = ownerMapService.findByLastName( owners.get( 0 )
                                                            .getLastName( ) );

        // Then
        assertThat( owner ).isEqualTo( owner );
    }

    @Test
    public void findOwnerByNotExistingLastNameIdTest( )
    {
        // Given
        List<Owner> owners = prepareOwners( );
        owners.forEach( owner -> ownerMapService.save( owner ) );

        // When
        Owner owner = ownerMapService.findByLastName( "Димитров" );

        // Then
        assertThat( owner ).isNull( );
    }

    @Test
    public void saveOwnerTest( )
    {
        // Given
        Owner testOwner = prepareOwner( );
        assertThat( testOwner.getId( ) ).isNull( );

        // When
        Owner actualOwner = ownerMapService.save( testOwner );

        // Then
        assertThat( actualOwner.getId( ) ).isNotNull( );
        assertThat( actualOwner.getId( ) ).isGreaterThan( 0L );
    }

    @Test
    public void saveOwnerWithPetsTest( )
    {
        // Given
        Owner testOwner = prepareOwner( );
        Pet pet = preparePet( );
        testOwner.addPet( pet );

        assertThat( testOwner.getId( ) ).isNull( );

        // When
        Owner actualOwner = ownerMapService.save( testOwner );

        // Then
        assertThat( actualOwner.getId( ) ).isNotNull( );
        assertThat( actualOwner.getId( ) ).isGreaterThan( 0L );
        assertThat( actualOwner.getPets( ) ).containsOnly( pet );
    }

    private Pet preparePet( )
    {
        Pet pet = new Pet( );
        pet.setName( "Джейк" );
        PetType petType = new PetType( );
        petType.setName( "Dog" );
        pet.setPetType( petType );
        return pet;
    }

    private Owner prepareOwner( )
    {
        Owner owner = new Owner( );
        owner.setFirstName( "Димитър" );
        owner.setLastName( "Димитров" );
        owner.setTelephone( "2346564" );
        owner.setCity( "Пловдив" );
        return owner;
    }


    private List<Owner> prepareOwners( )
    {
        List<Owner> owners = new ArrayList<>( );
        Owner o1 = new Owner( );
        o1.setId( 1L );
        o1.setFirstName( "Иван" );
        o1.setLastName( "Иванов" );
        Owner o2 = new Owner( );
        o2.setId( 2L );
        o2.setFirstName( "Марийка" );
        o2.setLastName( "Мариянова" );
        owners.add( o1 );
        owners.add( o2 );
        return owners;
    }
}