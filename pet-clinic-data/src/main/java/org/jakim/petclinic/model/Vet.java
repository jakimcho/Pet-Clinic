package org.jakim.petclinic.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Vet
        extends Person
{
    @ManyToMany( fetch = FetchType.EAGER )
    @JoinTable( name = "vet_specialty",
                joinColumns = @JoinColumn( name = "vet_id" ),
                inverseJoinColumns = @JoinColumn( name = "specialty_id" ) )
    private Set<Specialty> specialties = new HashSet<>( );

    public Set<Specialty> getSpecialties( )
    {
        return specialties;
    }

    public void setSpecialties( Set<Specialty> specialties )
    {
        this.specialties = specialties;
    }

    public Vet addSpecialty( final Specialty specialty )
    {
        this.specialties.add( specialty );
        return this;
    }
}
