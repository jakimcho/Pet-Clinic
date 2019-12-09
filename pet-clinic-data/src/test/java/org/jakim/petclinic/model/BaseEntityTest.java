package org.jakim.petclinic.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

abstract class BaseEntityTest<T extends BaseEntity>
{
    T entity;

    @Test
    void getId( )
    {
        entity.setId( 1L );
        Assertions.assertThat( this.entity.getId( ) )
                  .isEqualTo( 1L );
    }

    @Test
    void checkThatOwnerIsNew( )
    {
        entity.setId( null );
        Assertions.assertThat( this.entity.isNew( ) )
                  .isTrue( );
    }

    @Test
    void checkThatOwnerIsNotNewNew( )
    {
        entity.setId( 1L );
        Assertions.assertThat( this.entity.isNew( ) )
                  .isFalse( );
    }

    @Test
    void toStringTest( )
    {
        entity.setId( 1L );
        Assertions.assertThat( this.entity.toString() ).contains( "BaseEntity{id=1}" );
    }
}