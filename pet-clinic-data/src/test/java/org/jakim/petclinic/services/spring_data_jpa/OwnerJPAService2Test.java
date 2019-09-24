package org.jakim.petclinic.services.spring_data_jpa;

import org.jakim.petclinic.model.Owner;
import org.jakim.petclinic.repositories.OwnerRepository;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/*
 * This copy of the original OwnerJPAServiceTest test class is a show case demo of
 * Junit5 and MockitoExtension
 * */

@ExtendWith( MockitoExtension.class )
public class OwnerJPAService2Test
{

    @InjectMocks
    OwnerJPAService ownerJPAService;

    @Mock
    OwnerRepository ownerRepository;

    // Since we are using MockitoExtension and @InjectMocks we don;t need the setup method any more
/*  @BeforeEach
    public void setUp( )
            throws Exception
    {
        MockitoAnnotations.initMocks( this );
        this.ownerJPAService = new OwnerJPAService( ownerRepository );
    }
*/

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
        // Default calling times is 1
        verify( ownerRepository ).findByLastName( "Ivanov" );
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
        // Default calling times is 1
        verify( ownerRepository ).findAll( );

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
        // Default calling times is 1
        verify( ownerRepository ).findById( argumentCaptor.capture( ) );
        assertThat( argumentCaptor.getValue( ) ).isEqualTo( 1L );
    }

    @Test
    public void findByNotExistingId( )
    {
        //Given
        when( ownerRepository.findById( 2L ) )
                .thenReturn( Optional.empty( ) );
        ArgumentCaptor<Long> argumentCaptor = ArgumentCaptor.forClass( Long.class );

        //When
        Owner actualOwner = ownerJPAService.findById( 2L );

        //Then
        assertThat( actualOwner ).isNull( );
        // Default calling times is 1
        verify( ownerRepository ).findById( argumentCaptor.capture( ) );
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
        // Default calling times is 1
        verify( ownerRepository ).save( argumentCaptor.capture( ) );

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

        // Default calling times is 1
        verify( ownerRepository ).deleteById( argumentCaptor.capture( ) );

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

        // Default calling times is 1
        verify( ownerRepository ).delete( argumentCaptor.capture( ) );

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