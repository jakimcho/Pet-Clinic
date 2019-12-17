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
public class PetCommand
{
    @Getter @Setter
    private long id;
    @Getter @Setter
    private String name;
    @Getter @Setter
    private PetType petType;
    @Getter @Setter
    private Owner owner;
    @Getter @Setter
    private Set<Visit> visits = new HashSet<>( );
    @DateTimeFormat( pattern = "yyyy-MM-dd" )
    @Getter @Setter
    private LocalDate birthDate;

}
