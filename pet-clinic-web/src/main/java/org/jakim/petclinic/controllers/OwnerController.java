package org.jakim.petclinic.controllers;

import org.jakim.petclinic.model.Owner;
import org.jakim.petclinic.services.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Set;

@RequestMapping( "/owners" )
@Controller
public class OwnerController
{

    @Autowired
    private OwnerService ownerService;

    @RequestMapping( { "/", "" } )
    public String listOwners( Model model )
    {
        Set<Owner> owners = ownerService.findAll( );
        System.out.println( "We found " + owners.size( ) + " owners" );
        model.addAttribute( "owners",
                            owners );

        return "owners/index";
    }
}
