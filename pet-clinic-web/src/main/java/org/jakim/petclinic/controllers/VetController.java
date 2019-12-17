package org.jakim.petclinic.controllers;

import org.jakim.petclinic.model.Vet;
import org.jakim.petclinic.services.VetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class VetController
{
    private final static Logger LOGGER = LoggerFactory.getLogger( VetController.class );
    private final VetService vetService;

    public VetController( VetService vetService )
    {
        this.vetService = vetService;
    }

    @GetMapping( { "/vets/", "/vets" } )
    public String listVets( Model model )
    {
        LOGGER.info( "Inside listVets method" );
        Set<Vet> vets = vetService.findAll( );
        LOGGER.info( "Adding vets {} to the model",
                     vets.stream( )
                         .map( Object::toString )
                         .collect( Collectors.joining( ", " ) ) );
        model.addAttribute( "vets",
                            vets );

        LOGGER.info( "Exiting listVets method" );
        return "vets/index";
    }

    @GetMapping( "/api/vets" )
    public @ResponseBody
    Set<Vet> getVetsJSON( )
    {
        return vetService.findAll( );
    }
}
