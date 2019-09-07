package org.jakim.petclinic.repositories;

import org.jakim.petclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by jakim on 7.09.19 г.
 */
public interface VetRepository
        extends CrudRepository<Vet, Long>
{
}
