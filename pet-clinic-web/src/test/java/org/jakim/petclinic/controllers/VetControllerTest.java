package org.jakim.petclinic.controllers;

import org.jakim.petclinic.model.Vet;
import org.jakim.petclinic.services.VetService;
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
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class VetControllerTest
{

    VetController vetController;

    @Mock
    VetService vetService;

    @Mock
    Model model;

    @BeforeEach
    public void setUp( )
            throws Exception
    {
        MockitoAnnotations.initMocks( this );
        this.vetController = new VetController( vetService );
    }

    @Test
    public void testVetControllerMVC( )
            throws Exception
    {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup( this.vetController )
                                         .build( );

        mockMvc.perform( request( GET,
                                  "/vets/" ) )
               .andExpect( status( ).isOk( ) )
               .andExpect( view( ).name( "vets/index" ) );
    }

    @Test
    public void listVets( )
    {
        // Given
        when( vetService.findAll( ) ).thenReturn( getPreparedVets( ) );
        ArgumentCaptor<Set<Vet>> argumentCaptor = ArgumentCaptor.forClass( Set.class );
        // When
        String result = this.vetController.listVets( model );

        // Then
        assertThat( result,
                    is( equalTo( "vets/index" ) ) );

        verify( vetService,
                times( 1 ) ).findAll( );

        verify( model,
                times( 1 ) ).addAttribute( eq( "vets" ),
                                           argumentCaptor.capture( ) );

        Set<Vet> actualVets = argumentCaptor.getValue( );
        assertThat( actualVets,
                    hasSize( sizeOf( getPreparedVets( ) ) ) );

    }

    private Set<Vet> getPreparedVets( )
    {
        Set<Vet> vets = new HashSet<>( 3 );
        Vet vet1 = new Vet( );
        vet1.setFirstName( "Ivan" );
        Vet vet2 = new Vet( );
        vet2.setFirstName( "Milka" );
        Vet vet3 = new Vet( );
        vet3.setFirstName( "Aleksandur" );

        vets.add( vet1 );
        vets.add( vet2 );
        vets.add( vet3 );

        return vets;

    }
}