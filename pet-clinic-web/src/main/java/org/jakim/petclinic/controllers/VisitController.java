package org.jakim.petclinic.controllers;

import org.jakim.petclinic.model.Pet;
import org.jakim.petclinic.model.Visit;
import org.jakim.petclinic.services.PetService;
import org.jakim.petclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

/**
 * Created by jakim on 17.11.19 г.
 */
@Controller
public class VisitController
{
    private VisitService visitService;
    private PetService petService;

    public VisitController( VisitService visitService,
                            PetService petService )
    {
        this.visitService = visitService;
        this.petService = petService;
    }

    @ModelAttribute( "visit" )
    public Visit loadPetWithVisit( @PathVariable( "petId" ) long petId,
                                   Model model )
    {
        Pet pet = this.petService.findById( petId );
        model.addAttribute( pet );
        Visit visit = new Visit( );
        pet.addVisit( visit );
        return visit;
    }

    @GetMapping( "/owners/*/pets/{petId}/visits/new" )
    public String initNewVisitForm( @PathVariable( "petId" ) long petI )
    {
        return "pets/createOrUpdateVisitForm";
    }

    @PostMapping( "/owners/*/pets/{petId}/visits/new" )
    public String processNewVisitForm( @Valid Visit visit,
                                       BindingResult result )
    {
        if( result.hasErrors( ) )
        {
            return "pets/createOrUpdateVisitForm";
        } else
        {
            this.visitService.save( visit );
            return "redirect:/owners/" + visit.getPet( )
                                              .getOwner( )
                                              .getId( );
        }
    }
}
