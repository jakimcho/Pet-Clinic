package org.jakim.petclinic.controllers;

import org.jakim.petclinic.model.Owner;
import org.jakim.petclinic.model.Pet;
import org.jakim.petclinic.model.Visit;
import org.jakim.petclinic.services.PetService;
import org.jakim.petclinic.services.VisitService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith( MockitoExtension.class )
class VisitControllerTest
{
    @InjectMocks
    private VisitController visitController;

    @Mock
    private VisitService visitService;

    @Mock
    private PetService petService;

    @Mock
    private Model model;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp( )
    {
        mockMvc = MockMvcBuilders.standaloneSetup( this.visitController )
                                 .build( );
    }

    @Test
    void loadPetWithVisit( )
            throws Exception
    {
        //Given
        Pet pet = new Pet( );
        pet.setId( 2L );
        when( petService.findById( anyLong( ) ) ).thenReturn( pet );

        //When
        Visit visit = visitController.loadPetWithVisit( 1,
                                                        model );

        //Then
        assertThat( visit.getPet( ),
                    is( equalTo( pet ) ) );
        verify( model ).addAttribute( pet );
    }

    @Test
    void initNewVisitForm( )
            throws Exception
    {
        //Given
        Pet pet = new Pet( );
        pet.setId( 2L );
        when( petService.findById( anyLong( ) ) ).thenReturn( pet );

        //When & Then
        mockMvc.perform( request( GET,
                                  "/owners/1/pets/1/visits/new" ) )
               .andExpect( status( ).isOk( ) )
               .andExpect( view( ).name( "pets/createOrUpdateVisitForm" ) )
               .andExpect( model( ).attribute( "pet",
                                               pet ) );
    }

    @Test
    void processNewVisitForm( )
            throws Exception
    {
        //Given
        Pet pet = new Pet( );
        pet.setId( 2L );
        Owner owner = new Owner( );
        owner.setId( 5L );
        when( petService.findById( anyLong( ) ) ).thenReturn( pet );
        Visit visit = new Visit( );
        visit.setId( 2L );
        visit.setPet( pet );
        pet.setOwner( owner );

        //When & Then
        mockMvc.perform( request( POST,
                                  "/owners/1/pets/1/visits/new",
                                  visit ) )
               .andExpect( status( ).is3xxRedirection( ) )
               .andExpect( view( ).name( "redirect:/owners/" + owner.getId( ) ) );
    }
}