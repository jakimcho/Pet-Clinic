package org.jakim.petclinic.repositories;

import org.jakim.petclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jakim on 6.09.19 г.
 */
public interface OwnerRepository
        extends CrudRepository<Owner, Long>
{

}
