package org.jakim.petclinic.controllers;

import org.jakim.petclinic.model.Owner;
import org.jakim.petclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.Collections;
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
class OwnerController2Test
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
    void setUp( )
    {
        mockMvc = MockMvcBuilders.standaloneSetup( this.ownerController )
                                 .build( );
    }

    @Test
    void controllerListOwnersMapToRoot( )
            throws Exception
    {
        mockMvc.perform( request( GET,
                                  "/owners/" ) )
               .andExpect( status( ).isOk( ) )
               .andExpect( view( ).name( "owners/index" ) );
    }

    @Test
    void controllerListOwnersMapToRelative( )
            throws Exception
    {
        mockMvc.perform( request( GET,
                                  "/owners" ) )
               .andExpect( status( ).isOk( ) )
               .andExpect( view( ).name( "owners/index" ) );
    }

    @Test
    void controllerFindOwners( )
            throws Exception
    {
        mockMvc.perform( get( "/owners/find" ) )
               .andExpect( status( ).isOk( ) )
               .andExpect( view( ).name( "owners/findOwner" ) );
    }

    @Test
    void listOwners( )
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
    void displayExistingOwner( )
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
    void displayNotExistingOwner( )
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

    @Test
    void findOwner( )
            throws Exception
    {
        //when and then
        mockMvc.perform( get( "/owners/find" ) )
               .andExpect( status( ).isOk( ) )
               .andExpect( view( ).name( "owners/findOwner" ) )
               .andExpect( model( ).attributeExists( "owner" ) );
    }

    @Test
    void processFindForm_manyOwners( )
            throws Exception
    {
        // Given
        Set<Owner> expectedOwners = getPreparedOwners( );
        when( this.ownerService.findAllByLastNameLike( anyString( ) ) ).thenReturn( expectedOwners );
        //when and then
        mockMvc.perform( get( "/owners/doFind" ) )
               .andExpect( status( ).isOk( ) )
               .andExpect( view( ).name( "owners/ownersList" ) )
               .andExpect( model( ).attribute( "owners",
                                               equalTo( expectedOwners ) ) );
        verify( this.ownerService,
                never( ) ).findByLastName( anyString( ) );
        verify( this.ownerService ).findAllByLastNameLike( anyString( ) );
    }

    @Test
    void processFindForm_oneOwner( )
            throws Exception
    {
        // Given
        Owner owner = new Owner( );
        owner.setId( 1L );
        owner.setFirstName( "Ivan" );
        owner.setLastName( "Ivanov" );
        Set<Owner> expectedOwners = new HashSet<>( );
        expectedOwners.add( owner );

        when( this.ownerService.findAllByLastNameLike( anyString( ) ) ).thenReturn( expectedOwners );
        //when and then
        mockMvc.perform( get( "/owners/doFind" ) )
               .andExpect( status( ).is3xxRedirection( ) )
               .andExpect( view( ).name( "redirect:/owners/" + owner.getId( ) ) )
               .andExpect( model( ).attribute( "owner",
                                               hasProperty( "lastName",
                                                            is( "Ivanov" ) ) ) );
        verify( this.ownerService,
                never( ) ).findByLastName( anyString( ) );
        verify( this.ownerService,
                never( ) ).findAll( );
        verify( this.ownerService ).findAllByLastNameLike( anyString( ) );
    }

    @Test
    void processFindForm_noOwnerFound( )
            throws Exception
    {
        // Given
        when( this.ownerService.findAllByLastNameLike( anyString( ) ) ).thenReturn( Collections.emptySet( ) );

        //when and then
        mockMvc.perform( get( "/owners/doFind" ) )
               .andExpect( status( ).isNotFound( ) )
               .andExpect( view( ).name( "owners/ownersList" ) );
        verify( this.ownerService,
                never( ) ).findByLastName( anyString( ) );
        verify( this.ownerService,
                never( ) ).findAll( );
        verify( this.ownerService ).findAllByLastNameLike( anyString( ) );
    }

    @Test
    void processFindForm_noGetParams( )
            throws Exception
    {
        //Given
        Set<Owner> expectedOwners = getPreparedOwners( );
        ArgumentCaptor<String> args = ArgumentCaptor.forClass( String.class );
        when( ownerService.findAll( ) ).thenReturn( expectedOwners );
        Owner owner = new Owner( );
        owner.setLastName( null );

        //when and then
        mockMvc.perform( get( "/owners/doFind",
                              owner ) )
               .andExpect( status( ).isOk( ) )
               .andExpect( view( ).name( "owners/ownersList" ) )
               .andExpect( model( ).attribute( "owners",
                                               equalTo( expectedOwners ) ) );
        verify( this.ownerService ).findAll( );
        verify( this.ownerService,
                never( ) ).findAllByLastNameLike( anyString( ) );
        verify( this.ownerService,
                never( ) ).findAllByLastNameLike( anyString( ) );
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