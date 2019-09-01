package org.jakim.petclinic.bootstrap;

import org.jakim.petclinic.model.Owner;
import org.jakim.petclinic.model.PetType;
import org.jakim.petclinic.model.Vet;
import org.jakim.petclinic.services.OwnerService;
import org.jakim.petclinic.services.PetTypeService;
import org.jakim.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader
        implements CommandLineRunner
{
    private final OwnerService ownerService;
    private final VetService vetService;

    private final PetTypeService petTypeService;

    public DataLoader( OwnerService ownerService,
                       VetService vetService,
                       PetTypeService petTypeService )
    {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run( String... args )
            throws Exception
    {
        PetType petType1 = new PetType( );
        petType1.setName( "Dog" );
        petTypeService.save( petType1 );

        PetType petType2 = new PetType( );
        petType2.setName( "Cat" );
        petTypeService.save( petType2 );

        Owner owner1 = new Owner( );
        owner1.setFirstName( "Ivan" );
        owner1.setLastName( "Ivanov" );

        Owner owner2 = new Owner( );
        owner2.setFirstName( "Jordanka" );
        owner2.setLastName( "Fandukova" );

        Owner owner3 = new Owner( );
        owner3.setFirstName( "Bojko" );
        owner3.setLastName( "Borisov" );

        ownerService.save( owner1 );
        ownerService.save( owner2 );
        ownerService.save( owner3 );

        System.out.println( "Loaded Owners...." );

        Vet vet1 = new Vet( );
        vet1.setFirstName( "Ginka" );
        vet1.setLastName( "Kerkez" );

        Vet vet2 = new Vet( );
        vet2.setFirstName( "Dimitar" );
        vet2.setLastName( "Georgiev" );

        vetService.save( vet1 );
        vetService.save( vet2 );

        System.out.println( "Loaded Vets...." );
    }
}

