package org.jakim.petclinic.controllers;

import org.jakim.petclinic.model.Owner;
import org.jakim.petclinic.services.OwnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;
import java.util.stream.Collectors;

@RequestMapping( "/owners" )
@Controller
public class OwnerController
{
    private final OwnerService ownerService;
    private final Logger LOGGER = LoggerFactory.getLogger( OwnerController.class );

    public OwnerController( OwnerService ownerService )
    {
        this.ownerService = ownerService;
    }

    @RequestMapping( { "/", "" } )
    public String listOwners( Model model )
    {
        LOGGER.info( "Inside listOwners method" );
        Set<Owner> owners = ownerService.findAll( );
        LOGGER.info( "Adding {} to the model",
                     owners.stream( )
                           .map( Object::toString )
                           .collect( Collectors.joining( ", " ) ) );
        model.addAttribute( "owners",
                            owners );
        LOGGER.info( "Exiting listOwners method" );
        return "owners/index";
    }
}
