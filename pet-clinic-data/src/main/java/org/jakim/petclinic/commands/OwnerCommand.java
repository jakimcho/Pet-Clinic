package org.jakim.petclinic.commands;

import lombok.Getter;
import lombok.Setter;
import org.jakim.petclinic.model.Pet;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jakim on 17.12.19 г.
 */
@Getter
@Setter
public class OwnerCommand
{
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String telephone;
    private Set<Pet> pets = new HashSet<>( );
}
