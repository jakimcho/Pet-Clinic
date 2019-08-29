package org.jakim.petclinic.bootstrap;

import org.jakim.petclinic.model.Owner;
import org.jakim.petclinic.model.Vet;
import org.jakim.petclinic.services.OwnerService;
import org.jakim.petclinic.services.VetService;
import org.jakim.petclinic.services.map.OwnerMapService;
import org.jakim.petclinic.services.map.VetMapService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader
        implements CommandLineRunner
{
    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader( )
    {
        ownerService = new OwnerMapService( );
        vetService = new VetMapService( );
    }

    @Override
    public void run( String... args )
            throws Exception
    {
        Owner owner1 = new Owner( );
        owner1.setFirstName( "Ivan" );
        owner1.setLastName( "Ivanov" );
        owner1.setId( 1L );

        Owner owner2 = new Owner( );
        owner1.setFirstName( "Jordanka" );
        owner1.setLastName( "Fandukova" );
        owner1.setId( 2L );

        Owner owner3 = new Owner( );
        owner1.setFirstName( "Bojko" );
        owner1.setLastName( "Borisov" );
        owner1.setId( 3L );

        ownerService.save( owner1 );
        ownerService.save( owner2 );
        ownerService.save( owner3 );

        System.out.println( "Loaded Owners...." );

        Vet vet1 = new Vet( );
        vet1.setFirstName( "Ginka" );
        vet1.setLastName( "Kerkez" );
        vet1.setId( 1L );

        Vet vet2 = new Vet( );
        vet2.setFirstName( "Dimitar" );
        vet2.setLastName( "Georgiev" );
        vet2.setId( 1L );

        vetService.save( vet1 );
        vetService.save( vet2 );

        System.out.println( "Loaded Vets...." );
    }
}

