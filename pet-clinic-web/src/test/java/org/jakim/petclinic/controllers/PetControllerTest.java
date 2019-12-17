package org.jakim.petclinic.controllers;

import org.jakim.petclinic.commands.PetCommand;
import org.jakim.petclinic.convertors.PetCommandToPet;
import org.jakim.petclinic.convertors.PetToCommandPet;
import org.jakim.petclinic.model.Owner;
import org.jakim.petclinic.model.Pet;
import org.jakim.petclinic.model.PetType;
import org.jakim.petclinic.services.OwnerService;
import org.jakim.petclinic.services.PetService;
import org.jakim.petclinic.services.PetTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith( MockitoExtension.class )
class PetControllerTest
{
    @Mock
    private PetService petService;

    @Mock
    private PetTypeService petTypeService;

    @Mock
    private OwnerService ownerService;

    @Mock
    PetCommandToPet petCommandToPet;

    @Mock
    PetToCommandPet petToCommandPet;

    @InjectMocks
    PetController petController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp( )
            throws Exception
    {
        mockMvc = MockMvcBuilders.standaloneSetup( this.petController )
                                 .build( );
    }

    @Test
    void initCreationForm( )
            throws Exception
    {
        //Given
        Owner owner = getOwner( );
        when( ownerService.findById( anyLong( ) ) ).thenReturn( owner );

        //When Then
        this.mockMvc.perform( get( "/owners/1/pets/new" ) )
                    .andExpect( status( ).isOk( ) )
                    .andExpect( view( ).name( "pets/createOrUpdatePetForm" ) )
                    .andExpect( model( ).attributeExists( "pet",
                                                          "owner" ) );
        verifyZeroInteractions( ownerService );
    }

    @Test
    void processCreationForm( )
            throws Exception
    {
        //Given
        Owner owner = getOwner( );
        Pet pet = new Pet( );
        pet.setId( 3L );
        PetCommand petCommand = new PetCommand( );
        petCommand.setId( 3l );
        when( ownerService.findById( anyLong( ) ) ).thenReturn( owner );
        when( petService.save( any( ) ) ).thenReturn( pet );
        when( petCommandToPet.convert( any( ) ) ).thenReturn( pet );
        when( petToCommandPet.convert( any( ) ) ).thenReturn( petCommand );

        //When Then
        this.mockMvc.perform( post( "/owners/" +
                                    owner.getId( ) + "/pets/new" ) )
                    .andExpect( status( ).is3xxRedirection( ) )
                    .andExpect( view( ).name( "redirect:/owners/" + owner.getId( ) ) )
                    .andExpect( model( ).attributeExists( "owner",
                                                          "pet" ) );

        verify( ownerService ).findById( any( ) );
        verify( petService ).save( any( ) );
        verify( petCommandToPet ).convert( any( ) );
        verify( petToCommandPet ).convert( any( ) );
    }

    @Test
    void initUpdateForm( )
            throws Exception
    {
        //Given
        Owner owner = getOwner( );
        Pet pet = getPets( ).iterator( )
                            .next( );
        when( ownerService.findById( anyLong( ) ) ).thenReturn( owner );
        when( petService.findById( anyLong( ) ) ).thenReturn( pet );

        //When Then
        this.mockMvc.perform( get( "/owners/" + owner.getId( ) + "/pets/" + pet.getId( ) + "/edit" ) )
                    .andExpect( status( ).isOk( ) )
                    .andExpect( view( ).name( "pets/createOrUpdatePetForm" ) )
                    .andExpect( model( ).attributeExists( "pet",
                                                          "owner" ) );
        verify( ownerService ).findById( anyLong( ) );
        verify( petService ).findById( anyLong( ) );
    }

    @Test
    void processUpdateForm( )
            throws Exception
    {
        //Given
        Owner owner = getOwner( );
        Pet pet = new Pet( );
        pet.setId( 3L );
        pet.setName( "Ivanka" );
        PetCommand petCommand = new PetCommand( );
        petCommand.setId( 3l );
        when( ownerService.findById( anyLong( ) ) ).thenReturn( owner );
        when( petService.save( any( ) ) ).thenReturn( pet );

        when( petCommandToPet.convert( any( ) ) ).thenReturn( pet );
        when( petToCommandPet.convert( any( ) ) ).thenReturn( petCommand );
        ArgumentCaptor<Pet> petArgument = ArgumentCaptor.forClass( Pet.class );

        //When Then
        this.mockMvc.perform( post( "/owners/" + owner.getId( ) +
                                    "/pets/" + pet.getId( ) +
                                    "/edit" ).param( "name",
                                                     "Ivanka" ) )
                    .andExpect( status( ).is3xxRedirection( ) )
                    .andExpect( view( ).name( "redirect:/owners/" + owner.getId( ) ) )
                    .andExpect( model( ).attributeExists( "owner",
                                                          "pet" ) );

        verify( ownerService ).findById( any( ) );
        verify( petService ).save( petArgument.capture( ) );
        assertThat( petArgument.getValue( ),
                    hasProperty( "name",
                                 equalTo( "Ivanka" ) ) );

        verify( petCommandToPet ).convert( any( ) );
        verify( petToCommandPet ).convert( any( ) );
    }

    private Set<Pet> getPets( )
    {
        Set<Pet> pets = new HashSet<>( );

        PetType type1 = new PetType( );
        type1.setName( "Cat" );

        PetType type2 = new PetType( );
        type2.setName( "Dog" );

        Pet pet1 = new Pet( );
        pet1.setId( 1L );
        pet1.setName( "Katya" );
        pet1.setPetType( type1 );

        Pet pet2 = new Pet( );
        pet2.setId( 2L );
        pet2.setName( "Buck" );
        pet2.setPetType( type2 );

        Pet pet3 = new Pet( );
        pet3.setId( 3L );
        pet3.setName( "Tom" );
        pet3.setPetType( type1 );

        pets.add( pet1 );
        pets.add( pet2 );
        pets.add( pet3 );
        return pets;
    }

    private Owner getOwner( )
    {
        Owner owner = new Owner( );
        owner.setId( 1L );
        owner.setLastName( "Ivanov" );
        return owner;
    }
}