package org.jakim.petclinic.services.map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class AbstractMapService<T, ID>
{

    protected Map<ID, T> map = new HashMap<>( );
    protected Logger LOGGER = LoggerFactory.getLogger( this.getClass( )
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

    T save( ID id,
            T object )
    {
        LOGGER.info( "Inside save() method" );
        LOGGER.info( "Persisting {}: {}",
                     object.getClass( )
                           .getSimpleName( ),
                     object );
        map.put( id,
                 object );
        LOGGER.info( "Exiting save() method" );
        return map.get( id );
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

}
