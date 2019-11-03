package org.jakim.petclinic.controllers;

import org.jakim.petclinic.model.Owner;
import org.jakim.petclinic.model.Pet;
import org.jakim.petclinic.model.PetType;
import org.jakim.petclinic.services.OwnerService;
import org.jakim.petclinic.services.PetService;
import org.jakim.petclinic.services.PetTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Set;

/**
 * Created by jakim on 3.11.19 г.
 */
@Controller
@RequestMapping( "/owners/{ownerId}" )
public class PetController
{
    private final static Logger LOGGER = LoggerFactory.getLogger( PetController.class );
    private static final String PETS_CREATE_OR_UPDATE_PET_FORM = "pets/createOrUpdatePetForm";
    private final PetService petService;
    private final OwnerService ownerService;
    private final PetTypeService petTypeService;

    public PetController( PetService petService,
                          OwnerService ownerService,
                          PetTypeService petTypeService )
    {
        this.petService = petService;
        this.ownerService = ownerService;
        this.petTypeService = petTypeService;
    }

    @ModelAttribute( "petTypes" )
    public Set<PetType> populatePetTypes( )
    {
        return this.petTypeService.findAll( );
    }

    @ModelAttribute( "owner" )
    public Owner populatePetTypes( @PathVariable Long ownerId )
    {
        return this.ownerService.findById( ownerId );
    }

    @InitBinder( "owner" )
    public void initOwnerBinder( WebDataBinder dataBinder )
    {
        dataBinder.setDisallowedFields( "id" );
    }

    @GetMapping( "/pets/new" )
    public ModelAndView initCreationForm( Owner owner,
                                          ModelAndView mov )
    {
        Pet pet = new Pet( );
        owner.addPet( pet );
        mov.addObject( pet );
        mov.setViewName( PETS_CREATE_OR_UPDATE_PET_FORM );
        return mov;
    }

    @PostMapping( "/pets/new" )
    public String processCreationForm( Owner owner,
                                       @Valid Pet pet,
                                       BindingResult result,
                                       Model model )
    {

        if( StringUtils.hasLength( pet.getName( ) ) && pet.isNew( ) && owner.getPet( pet.getName( ),
                                                                                     true ) != null )
        {
            result.rejectValue( "name",
                                "duplicate",
                                "already exists" );
        }

        if( result.hasErrors( ) )
        {
            model.addAttribute( "pet",
                                pet );
            return PETS_CREATE_OR_UPDATE_PET_FORM;
        } else
        {
            owner.addPet( pet );
            Pet savedPet = this.petService.save( pet );
            return "redirect:/owners/" + owner.getId( );
        }
    }


    @GetMapping( "/pets/{petId}/edit" )
    public ModelAndView initUpdateForm( Owner owner,
                                        @PathVariable Long petId,
                                        ModelAndView mov )
    {
        mov.addObject( petService.findById( petId ) );
        mov.setViewName( PETS_CREATE_OR_UPDATE_PET_FORM );
        return mov;
    }

    @PostMapping( { "/pets/{petId}/edit" } )
    public String processUpdateForm( Owner owner,
                                     @Valid Pet pet,
                                     BindingResult result,
                                     Model model)
    {
        if( result.hasErrors( ) )
        {
            model.addAttribute( "pet",
                                pet );
            return PETS_CREATE_OR_UPDATE_PET_FORM;
        } else
        {
            owner.addPet( pet );
            Pet savedPet = this.petService.save( pet );
            return "redirect:/owners/" + owner.getId( );
        }
    }

}
