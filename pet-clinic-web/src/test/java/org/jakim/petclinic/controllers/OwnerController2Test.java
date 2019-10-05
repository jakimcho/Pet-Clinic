package org.jakim.petclinic.controllers;

import org.jakim.petclinic.model.Owner;
import org.jakim.petclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.util.IterableUtil.sizeOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/*
 * This copy of the original OwnerJPAServiceTest test class is a show case demo of
 * Junit5 and MockitoExtension
 * */

@ExtendWith( MockitoExtension.class )
public class OwnerController2Test
{

    @InjectMocks
    OwnerController ownerController;

    @Mock
    private OwnerService ownerService;

    @Mock
    private Model model;

    MockMvc mockMvc;

    // No need of this setUp method since we are using MockitoExtension and @InjectMocks
/*
    @BeforeEach
    public void setUp( )
            throws Exception
    {
        MockitoAnnotations.initMocks( this );

        this.ownerController = new OwnerController( ownerService );
    }
*/

    @BeforeEach
    public void setUp( )
    {
        mockMvc = MockMvcBuilders.standaloneSetup( this.ownerController )
                                 .build( );
    }

    @Test
    public void controllerListOwnersMapToRoot( )
            throws Exception
    {
        mockMvc.perform( request( GET,
                                  "/owners/" ) )
               .andExpect( status( ).isOk( ) )
               .andExpect( view( ).name( "owners/index" ) );
    }

    @Test
    public void controllerListOwnersMapToRelative( )
            throws Exception
    {
        mockMvc.perform( request( GET,
                                  "/owners" ) )
               .andExpect( status( ).isOk( ) )
               .andExpect( view( ).name( "owners/index" ) );
    }

    @Test
    public void controllerFindOwners( )
            throws Exception
    {
        mockMvc.perform( get( "/owners/find" ) )
               .andExpect( status( ).isOk( ) )
               .andExpect( view( ).name( "owners/find" ) );
    }

    @Test
    public void listOwners( )
    {
        // Given
        when( ownerService.findAll( ) ).thenReturn( getPreparedOwners( ) );
        ArgumentCaptor<Set<Owner>> modelArgument = ArgumentCaptor.forClass( Set.class );

        // When
        String actualResult = this.ownerController.listOwners( model );

        // Then
        assertThat( actualResult,
                    is( equalTo( "owners/index" ) ) );
        verify( ownerService,
                times( 1 ) ).findAll( );
        verify( model,
                times( 1 ) ).addAttribute( eq( "owners" ),
                                           modelArgument.capture( ) );

        Set<Owner> actualOwners = modelArgument.getValue( );
        assertThat( actualOwners,
                    hasSize( sizeOf( getPreparedOwners( ) ) ) );

    }

    @Test
    public void displayExistingOwner( )
            throws Exception
    {
        // Given
        Owner owner = new Owner( );
        owner.setId( 1L );
        owner.setFirstName( "Ivan" );
        owner.setLastName( "Ivanov" );

        when( this.ownerService.findById( anyLong( ) ) ).thenReturn( owner );

        //when and then
        mockMvc.perform( get( "/owners/1" ) )
               .andExpect( status( ).isOk( ) )
               .andExpect( view( ).name( "owners/ownerDetails" ) )
               .andExpect( model( ).attribute( "owner",
                                               hasProperty( "id",
                                                            is( 1L ) ) ) );
        verify( this.ownerService ).findById( 1L );
    }

    @Test
    public void displayNotExistinggOwner( )
            throws Exception
    {
        // Given
        when( this.ownerService.findById( anyLong( ) ) ).thenReturn( null );

        //when and then
        mockMvc.perform( get( "/owners/1" ) )
               .andExpect( status( ).isNotFound( ) )
               .andExpect( view( ).name( "owners/ownerDetails" ) );
        verify( this.ownerService ).findById( 1L );
    }
    ///////////////////////////// Helpers //////////////////////////////////////

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