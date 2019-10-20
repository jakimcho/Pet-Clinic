package org.jakim.petclinic.repositories;

import org.jakim.petclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * Created by jakim on 6.09.19 г.
 */
public interface OwnerRepository
        extends CrudRepository<Owner, Long>
{
    Owner findByLastName( String lastName );

    Set<Owner> findAllByLastNameLike( String lastName );

    Set<Owner> findAllByLastNameIgnoreCase( String lastName );

}
