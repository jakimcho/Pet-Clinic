package org.jakim.petclinic.services;

import java.util.Set;

public interface CrudService<T, ID> {

    Set<T> findAll();

    T find(ID id);

    T save(T object);

    void deleteById(ID id);

    void delete(T object);
}
