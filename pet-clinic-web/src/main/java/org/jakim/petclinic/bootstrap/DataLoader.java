package org.jakim.petclinic.bootstrap;

import org.jakim.petclinic.model.Owner;
import org.jakim.petclinic.model.Pet;
import org.jakim.petclinic.model.PetType;
import org.jakim.petclinic.model.Vet;
import org.jakim.petclinic.services.OwnerService;
import org.jakim.petclinic.services.PetTypeService;
import org.jakim.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

        PetType petType2 = new PetType( );
        petType2.setName( "Cat" );

        Pet pet1 = new Pet( );
        pet1.setPetType( petType1 );
        pet1.setBirthDate( LocalDate.now( ) );

        Pet pet2 = new Pet( );
        pet2.setPetType( petType2 );
        pet2.setBirthDate( LocalDate.now( )
                                    .minusYears( 5 ) );

        Pet pet3 = new Pet( );
        pet3.setPetType( petType2 );
        pet3.setBirthDate( LocalDate.now( )
                                    .minusYears( 7 ) );

        Pet pet4 = new Pet( );
        pet4.setPetType( petType1 );
        pet4.setBirthDate( LocalDate.now( )
                                    .minusYears( 2 ) );

        Owner owner1 = new Owner( );
        owner1.setFirstName( "Ivan" );
        owner1.setLastName( "Ivanov" );
        owner1.setAddress( "str. Marica 21" );
        owner1.setCity( "Plovdiv" );
        owner1.setTelephone( "234324" );
        Set<Pet> owner1Pets = new HashSet<>( );
        owner1Pets.add( pet1 );
        pet1.setOwner( owner1 );
        owner1.setPets( owner1Pets );

        Owner owner2 = new Owner( );
        owner2.setFirstName( "Jordanka" );
        owner2.setLastName( "Fandukova" );
        owner2.setAddress( "str. Sokol 2" );
        owner2.setCity( "Blagoevgrad" );
        owner2.setTelephone( "98673568" );
        Set<Pet> owner2Pets = new HashSet<>( );
        owner2Pets.add( pet2 );
        owner2Pets.add( pet4 );
        pet2.setOwner( owner2 );
        pet4.setOwner( owner2 );
        owner2.setPets( owner2Pets );

        Owner owner3 = new Owner( );
        owner3.setFirstName( "Bojko" );
        owner3.setLastName( "Borisov" );
        owner3.setAddress( "str. Lule Burgas 5" );
        owner3.setCity( "Sofia" );
        owner3.setTelephone( "00893424" );
        Set<Pet> owner3Pets = new HashSet<>( );
        owner3Pets.add( pet3 );
        pet3.setOwner( owner3 );
        owner3.setPets( owner3Pets );

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

