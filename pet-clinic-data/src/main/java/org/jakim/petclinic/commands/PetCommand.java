package org.jakim.petclinic.commands;

import lombok.Getter;
import lombok.Setter;
import org.jakim.petclinic.model.Owner;
import org.jakim.petclinic.model.PetType;
import org.jakim.petclinic.model.Visit;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jakim on 17.12.19 г.
 */
@Getter
@Setter
public class PetCommand
{

    private long id;
    private String name;
    private PetType petType;
    private Owner owner;
    private Set<Visit> visits = new HashSet<>( );
    private LocalDate birthDate;

}
