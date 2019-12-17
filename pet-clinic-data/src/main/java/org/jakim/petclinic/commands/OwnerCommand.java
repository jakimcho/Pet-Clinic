package org.jakim.petclinic.commands;

import lombok.Getter;
import lombok.Setter;
import org.jakim.petclinic.model.Pet;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jakim on 17.12.19 г.
 */
public class OwnerCommand
{

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String firstName;

    @Getter
    @Setter
    private String lastName;

    @Getter
    @Setter
    private String address;

    @Getter
    @Setter
    private String city;

    @Getter
    @Setter
    private String telephone;

    @OneToMany( cascade = CascadeType.ALL,
                mappedBy = "owner" )
    @Getter
    @Setter
    private Set<Pet> pets = new HashSet<>( );
}
