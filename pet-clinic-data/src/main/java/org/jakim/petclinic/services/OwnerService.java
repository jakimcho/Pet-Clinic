package org.jakim.petclinic.services;

import org.jakim.petclinic.model.Owner;

import java.util.Set;

public interface OwnerService
        extends CrudService<Owner, Long>
{

    Owner findByLastName( String lastName );

    Set<Owner> findAllByLastName( String lastName );
}
