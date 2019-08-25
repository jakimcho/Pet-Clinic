package org.jakim.petclinic.services;

import org.jakim.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>  {

    Owner findByLastName(String lastName);
}
