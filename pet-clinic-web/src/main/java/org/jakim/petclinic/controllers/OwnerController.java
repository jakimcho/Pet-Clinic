package org.jakim.petclinic.controllers;

import org.jakim.petclinic.model.Owner;
import org.jakim.petclinic.services.OwnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@RequestMapping( "/owners" )
@Controller
public class OwnerController
{
    private final static Logger LOGGER = LoggerFactory.getLogger( OwnerController.class );
    private final OwnerService ownerService;

    public OwnerController( OwnerService ownerService )
    {
        this.ownerService = ownerService;
    }

    @GetMapping( { "/", "" } )
    public String listOwners( Model model )
    {
        LOGGER.info( "Inside listOwners method" );
        Set<Owner> owners = ownerService.findAll( );
        LOGGER.info( "Adding {} owners  to the model",
                     owners.size( ) );
        model.addAttribute( "owners",
                            owners );
        LOGGER.info( "Exiting listOwners method" );
        return "owners/index";
    }

    @GetMapping( "/find" )
    public String findOwners( )
    {
        return "Not Implemented...";
    }

    @GetMapping( { "/{ownerId/}", "/{ownerId}" } )
    public ModelAndView showOwner( @PathVariable( "ownerId" ) Long ownerId )
    {
        ModelAndView mav = new ModelAndView( "owners/ownerDetails" );
        Owner theOwner = this.ownerService.findById( ownerId );
        if( theOwner == null )
        {
            mav.setStatus( HttpStatus.NOT_FOUND );
        } else
        {
            mav.addObject( theOwner );
        }

        return mav;
    }
}
