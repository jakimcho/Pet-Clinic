package org.jakim.petclinic.services.map;

import org.jakim.petclinic.model.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public abstract class AbstractMapService<T extends BaseEntity, ID extends Long>
{

    private Map<Long, T> map = new HashMap<>( );
    private Logger LOGGER = LoggerFactory.getLogger( this.getClass( )
                                                           .getName( ) );

    Set<T> findAll( )
    {
        LOGGER.info( "Inside findAll() method" );
        Set<T> data = new HashSet<>( map.values( ) );
        LOGGER.debug( "Found {} records",
                      data.size( ) );
        LOGGER.info( "Exiting findAll() method" );
        return data;
    }

    T findById( ID id )
    {
        return map.get( id );
    }

    T save( T object )
    {
        LOGGER.info( "Inside save() method" );
        if( object == null )
        {
            throw new RuntimeException( "Cannot persist object null" );
        }

        if( object.getId( ) == null )
        {
            object.setId( getNextId( ) );
        }

        LOGGER.info( "Persisting {}: {}",
                     object.getClass( )
                           .getSimpleName( ),
                     object );
        map.put( object.getId( ),
                 object );
        LOGGER.info( "Exiting save() method" );
        return object;
    }

    void deleteById( ID id )
    {
        map.remove( id );
    }

    void delete( T object )
    {
        map.entrySet( )
           .removeIf( entry->entry.getValue( )
                                  .equals( object ) );
    }

    private Long getNextId( )
    {
        return map.isEmpty( )
               ? 1L
               : Collections.max( map.keySet( ) ) + 1;
    }

}
