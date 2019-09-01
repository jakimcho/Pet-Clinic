package org.jakim.petclinic.model;

import org.assertj.core.api.Assertions;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

@Ignore
public class BaseEntityTest<T extends BaseEntity>
{
    protected T entity;

    @Test
    public void getId( )
    {
        entity.setId( 1L );

        Assertions.assertThat( this.entity.getId( ) )
                  .isEqualTo( 1L );
    }
}