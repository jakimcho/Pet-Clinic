package org.jakim.petclinic.services.spring_data_jpa;

import org.jakim.petclinic.model.Owner;
import org.jakim.petclinic.repositories.OwnerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.IterableUtil.sizeOf;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class OwnerJPAServiceTest
{

    OwnerJPAService ownerJPAService;

    @Mock
    OwnerRepository ownerRepository;

    @BeforeEach
    public void setUp( )
            throws Exception
    {
        MockitoAnnotations.initMocks( this );
        this.ownerJPAService = new OwnerJPAService( ownerRepository );
    }

    @Test
    public void findByLastName( )
    {
        //Given
        Owner expectedOwner = new Owner( );
        expectedOwner.setLastName( "Ivanov" );
        when( ownerRepository.findByLastName( anyString( ) ) ).thenReturn( expectedOwner );

        //When
        Owner actualOwner = ownerJPAService.findByLastName( "Ivanov" );

        //Then
        assertThat( actualOwner ).hasFieldOrPropertyWithValue( "lastName",
                                                               "Ivanov" );
        verify( ownerRepository,
                times( 1 ) ).findByLastName( "Ivanov" );
    }

    @Test
    public void findAll( )
    {
        //Given
        Set<Owner> testOwners = getPreparedOwners( );
        when( ownerRepository.findAll( ) )
                .thenReturn( testOwners );

        //When
        Set<Owner> actualOwners = ownerJPAService.findAll( );

        //Then
        assertThat( actualOwners ).hasSize( sizeOf( testOwners ) );
        verify( ownerRepository,
                times( 1 ) ).findAll( );

        // TODO: Once owner equals/hash methods are implemented uncomment bellow check
        // assertThat( actualOwners ).containsExactlyInAnyOrderElementsOf( testOwners );
    }

    @Test
    public void findById( )
    {
        //Given
        Owner expectedOwner = new Owner( );
        expectedOwner.setLastName( "Ivanov" );
        expectedOwner.setId( 1l );
        when( ownerRepository.findById( anyLong( ) ) )
                .thenReturn( Optional.of( expectedOwner ) );
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass( Long.class );

        //When
        Owner actualOwner = ownerJPAService.findById( 1L );

        //Then
        assertThat( actualOwner ).hasFieldOrPropertyWithValue( "id",
                                                               1L )
                                 .hasFieldOrPropertyWithValue( "lastName",
                                                               "Ivanov" );
        verify( ownerRepository,
                times( 1 ) ).findById( argumentCaptor.capture( ) );
        assertThat( argumentCaptor.getValue( ) ).isEqualTo( 1L );
    }

    @Test
    public void findByNotExistingId( )
    {
        //Given
        Owner expectedOwner = new Owner( );
        expectedOwner.setLastName( "Ivanov" );
        expectedOwner.setId( 1l );
        when( ownerRepository.findById( 1L ) )
                .thenReturn( Optional.of( expectedOwner ) );
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass( Long.class );

        //When
        Owner actualOwner = ownerJPAService.findById( 2L );

        //Then
        assertThat( actualOwner ).isNull( );
        verify( ownerRepository,
                times( 1 ) ).findById( argumentCaptor.capture( ) );
        assertThat( argumentCaptor.getValue( ) ).isEqualTo( 2L );
    }

    @Test
    public void save( )
    {
        //Given
        Owner expectedOwner = new Owner( );
        expectedOwner.setLastName( "Ivanov" );

        when( ownerRepository.save( expectedOwner ) )
                .thenReturn( expectedOwner );
        ArgumentCaptor<Owner> argumentCaptor = ArgumentCaptor.forClass( Owner.class );

        //When
        Owner actualOwner = ownerJPAService.save( expectedOwner );

        //Then
        assertThat( actualOwner ).hasFieldOrPropertyWithValue( "lastName",
                                                               "Ivanov" );
        verify( ownerRepository,
                times( 1 ) ).save( argumentCaptor.capture( ) );

        assertThat( argumentCaptor.getValue( ) ).hasFieldOrPropertyWithValue( "lastName",
                                                                              "Ivanov" );
    }

    @Test
    public void deleteById( )
    {
        //Given
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass( Long.class );

        //When
        ownerJPAService.deleteById( 1L );

        //Then

        verify( ownerRepository,
                times( 1 ) ).deleteById( argumentCaptor.capture( ) );

        assertThat( argumentCaptor.getValue( ) ).isEqualTo( 1L );
    }

    @Test
    public void delete( )
    {
        //Given
        Owner expectedOwner = new Owner( );
        expectedOwner.setLastName( "Ivanov" );
        ArgumentCaptor<Owner> argumentCaptor = ArgumentCaptor.forClass( Owner.class );

        //When
        ownerJPAService.delete( expectedOwner );

        //Then
        verify( ownerRepository,
                times( 1 ) ).delete( argumentCaptor.capture( ) );

        assertThat( argumentCaptor.getValue( ) ).hasFieldOrPropertyWithValue( "lastName",
                                                                              "Ivanov" );
    }

    private Set<Owner> getPreparedOwners( )
    {
        Set<Owner> owners = new HashSet<>( 3 );
        Owner o1 = new Owner( );
        o1.setFirstName( "Ivan" );
        o1.setLastName( "Ivanov" );

        Owner o2 = new Owner( );
        o2.setFirstName( "Yoana" );
        o2.setLastName( "Racheva" );

        Owner o3 = new Owner( );
        o3.setFirstName( "Dimitur" );
        o3.setLastName( "Petrov" );

        owners.add( o1 );
        owners.add( o2 );
        owners.add( o3 );
        return owners;
    }
}