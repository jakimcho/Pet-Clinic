package org.jakim.petclinic.controllers;

import org.jakim.petclinic.model.Vet;
import org.jakim.petclinic.services.map.VetMapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;
import java.util.stream.Collectors;

@RequestMapping( "/vets" )
@Controller
public class VetController
{
    private final static Logger LOGGER = LoggerFactory.getLogger( VetController.class );
    private final VetMapService vetMapService;

    public VetController( VetMapService vetMapService )
    {
        this.vetMapService = vetMapService;
    }

    @RequestMapping( { "/", "" } )
    public String listVets( Model model )
    {
        LOGGER.info( "Inside listVets method" );
        Set<Vet> vets = vetMapService.findAll( );
        LOGGER.info( "Adding vets {} to the model",
                     vets.stream( )
                         .map( Object::toString )
                         .collect( Collectors.joining( ", " ) ) );
        model.addAttribute( "vets",
                            vets );

        LOGGER.info( "Exiting listVets method" );
        return "vets/index";
    }
}
