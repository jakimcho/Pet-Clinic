package org.jakim.petclinic.bootstrap;

import org.jakim.petclinic.model.*;
import org.jakim.petclinic.services.OwnerService;
import org.jakim.petclinic.services.PetTypeService;
import org.jakim.petclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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
    {
        PetType petType1 = new PetType( );
        petType1.setName( "Dog" );

        PetType petType2 = new PetType( );
        petType2.setName( "Cat" );

        Pet pet1 = new Pet( );
        pet1.setName( "Spike" );
        pet1.setPetType( petType1 );
        pet1.setBirthDate( LocalDate.now( ) );

        Pet pet2 = new Pet( );
        pet2.setName( "Lila" );
        pet2.setPetType( petType2 );
        pet2.setBirthDate( LocalDate.now( )
                                    .minusYears( 5 ) );

        Pet pet3 = new Pet( );
        pet3.setName( "Tom" );
        pet3.setPetType( petType2 );
        pet3.setBirthDate( LocalDate.now( )
                                    .minusYears( 7 ) );

        Pet pet4 = new Pet( );
        pet4.setName( "Sami" );
        pet4.setPetType( petType1 );
        pet4.setBirthDate( LocalDate.now( )
                                    .minusYears( 2 ) );


        Owner owner1 = new Owner( );
        owner1.setFirstName( "Ivan" );
        owner1.setLastName( "Ivanov" );
        owner1.setAddress( "str. Marica 21" );
        owner1.setCity( "Plovdiv" );
        owner1.setTelephone( "234324" );
        owner1.addPet( pet1 )
              .addPet( pet4 );
        ownerService.save( owner1 );

        Owner owner3 = new Owner( );
        owner3.setFirstName( "Bojko" );
        owner3.setLastName( "Borisov" );
        owner3.setAddress( "str. Lule Burgas 5" );
        owner3.setCity( "Sofia" );
        owner3.setTelephone( "00893424" );
        owner3.addPet( pet2 )
              .addPet( pet3 );
        ownerService.save( owner3 );


        System.out.println( "Loaded Owners...." );

        Specialty specialty1 = new Specialty( );
        specialty1.setDescription( "Bones" );

        Specialty specialty2 = new Specialty( );
        specialty2.setDescription( "General" );

        Specialty specialty3 = new Specialty( );
        specialty3.setDescription( "Derma" );

        Specialty specialty4 = new Specialty( );
        specialty4.setDescription( "Parasites" );


        Vet vet1 = new Vet( );
        vet1.setFirstName( "Ginka" );
        vet1.setLastName( "Kerkez" );
        vet1.addSpecialty( specialty1 );
        vet1.addSpecialty( specialty3 );
        vetService.save( vet1 );

        Vet vet2 = new Vet( );
        vet2.setFirstName( "Dimitar" );
        vet2.setLastName( "Georgiev" );
        vet2.addSpecialty( specialty1 );
        vet2.addSpecialty( specialty2 );
        vet2.addSpecialty( specialty4 );
        vetService.save( vet2 );

        System.out.println( "Loaded Vets...." );
    }
}

