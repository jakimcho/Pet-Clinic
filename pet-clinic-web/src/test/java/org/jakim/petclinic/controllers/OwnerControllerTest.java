package org.jakim.petclinic.controllers;

import org.jakim.petclinic.model.Owner;
import org.jakim.petclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class OwnerControllerTest
{

    OwnerController ownerController;

    @Mock
    private OwnerService ownerService;

    @Mock
    private Model model;

    @BeforeEach
    public void setUp( )
            throws Exception
    {
        MockitoAnnotations.initMocks( this );

        this.ownerController = new OwnerController( ownerService );
    }

    @Test
    public void testOwnerControllerMVC( )
            throws Exception
    {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup( this.ownerController )
                                         .build( );

        mockMvc.perform( request( GET,
                                  "/owners/" ) )
               .andExpect( status( ).isOk( ) )
               .andExpect( view( ).name( "owners/index" ) );
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