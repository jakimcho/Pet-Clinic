package org.jakim.petclinic.controllers;

import org.jakim.petclinic.model.Owner;
import org.jakim.petclinic.model.PetType;
import org.jakim.petclinic.services.OwnerService;
import org.jakim.petclinic.services.PetService;
import org.jakim.petclinic.services.PetTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

/**
 * Created by jakim on 3.11.19 г.
 */
@RequestMapping( "/pets" )
@Controller
public class PetController
{
    private final static Logger LOGGER = LoggerFactory.getLogger( PetController.class );
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

    @ModelAttribute( "types" )
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

    public ModelAndView listOwnerPet( )
    {
        ModelAndView mov = new ModelAndView( );

        return mov;
    }

}
