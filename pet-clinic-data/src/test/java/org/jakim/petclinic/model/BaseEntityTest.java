package org.jakim.petclinic.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public abstract class BaseEntityTest<T extends BaseEntity>
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