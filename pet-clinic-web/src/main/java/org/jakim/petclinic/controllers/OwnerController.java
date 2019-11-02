package org.jakim.petclinic.controllers;

import org.jakim.petclinic.model.Owner;
import org.jakim.petclinic.services.OwnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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

    @GetMapping( { "/", "", "/ownersList" } )
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

    @GetMapping( { "/find", "/find/" } )
    public ModelAndView findOwner( )
    {
        LOGGER.info( "Inside findOwner method" );
        ModelAndView mav = new ModelAndView( "owners/findOwner" );
        Owner searchedOwner = new Owner( );
        mav.addObject( searchedOwner );
        LOGGER.info( "Exiting findOwner method" );
        return mav;
    }

    @GetMapping( "/doFind" )
    public ModelAndView processFindForm( final Owner owner,
                                         final BindingResult result )
    {
        ModelAndView mav = new ModelAndView( );
        LOGGER.info( "Inside processFindForm method" );
        // Allow to list all owners if no Get parameters are provided
        if( owner.getLastName( ) == null )
        {
            owner.setLastName( "" );
        }

        LOGGER.info( "Searching for owner with last name '{}'",
                     owner.getLastName( ) );
        Set<Owner> foundOwners = owner.getLastName( )
                                      .isEmpty( )
                                 ? this.ownerService.findAll( )
                                 : this.ownerService.findAllByLastNameLike( "%" + owner.getLastName( ) + "%" );
        if( foundOwners.isEmpty( ) )
        {
            LOGGER.warn( "Owner with last name {} was not found in the DB",
                         owner.getLastName( ) );
            result.rejectValue( "lastName",
                                "notFound",
                                "not found" );
            mav.setStatus( HttpStatus.NOT_FOUND );
            mav.setViewName( "owners/index" );
            LOGGER.info( "Exiting processFindForm method" );
            return mav;
        }

        if( foundOwners.size( ) == 1 )
        {
            Owner foundOwner = foundOwners.iterator( )
                                          .next( );
            LOGGER.debug( "Found one owner {}",
                          owner );
            mav.addObject( foundOwner );
            mav.setViewName( "redirect:/owners/" + foundOwner.getId( ) );
        } else
        {
            LOGGER.debug( "Found {} owners ",
                          foundOwners.size( ) );

            mav.addObject( "owners",
                           foundOwners );
            mav.setViewName( "owners/index" );
        }

        LOGGER.info( "Exiting processFindForm method" );
        return mav;
    }

    @GetMapping( { "/{ownerId/}", "/{ownerId}" } )
    public ModelAndView showOwner( @PathVariable( "ownerId" ) Long ownerId )
    {
        LOGGER.info( "Inside showOwner method" );
        ModelAndView mav = new ModelAndView( "owners/ownerDetails" );
        LOGGER.info( "Searching owner with id {}",
                     ownerId );
        Owner theOwner = this.ownerService.findById( ownerId );
        if( theOwner == null )
        {
            LOGGER.warn( "Did not found owner with id {}",
                         ownerId );
            mav.setStatus( HttpStatus.NOT_FOUND );
        } else
        {
            LOGGER.debug( "Found owner with id {}",
                          theOwner );
            mav.addObject( theOwner );
        }

        LOGGER.info( "Exiting showOwner method" );
        return mav;
    }

    @GetMapping( "/new" )
    public ModelAndView initCreationForm( )
    {
        ModelAndView mav = new ModelAndView( );
        Owner owner = new Owner( );
        mav.addObject( owner );
        mav.setViewName( "owners/createOrUpdateOwner" );
        return mav;
    }

    @PostMapping( "/new" )
    public String processCreationForm( @Valid Owner owner,
                                       BindingResult result )
    {
        if( result.hasErrors( ) )
        {
            return "owners/createOrUpdateOwner";
        } else
        {
            Owner savedOwner = this.ownerService.save( owner );
            return "redirect:/owners/" + savedOwner.getId( );
        }
    }

    @GetMapping( "/{ownerId}/edit" )
    public ModelAndView initUpdateForm( @PathVariable( "ownerId" ) Long ownerId,
                                        ModelAndView mov )
    {
        ModelAndView mav = new ModelAndView( );
        Owner owner = this.ownerService.findById( ownerId );
        mav.addObject( owner );
        mav.setViewName( "owners/createOrUpdateOwner" );
        return mav;
    }

    @PostMapping( "/{ownerId}/edit" )
    public String processUpdateForm( @Valid Owner owner,
                                     @PathVariable( "ownerId" ) Long ownerId,
                                     BindingResult result )
    {
        //TODO: add logic to update the owner having id ownerId
        if( result.hasErrors( ) )
        {
            return "owners/createOrUpdateOwner";
        } else
        {
            Owner savedOwner = this.ownerService.save( owner );
            return "redirect:/owners/" + savedOwner.getId( );
        }
    }

}
