package micha.udemy.petclinic.bootstrap;

import micha.udemy.petclinic.model.Owner;
import micha.udemy.petclinic.model.Pet;
import micha.udemy.petclinic.model.PetType;
import micha.udemy.petclinic.model.Vet;
import micha.udemy.petclinic.service.OwnerService;
import micha.udemy.petclinic.service.PetTypeService;
import micha.udemy.petclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatType = petTypeService.save(cat);

        createAndSaveOwner(1L, "Michael", "Weston", "123 Brickerel", "Miami", "123456789", savedDogType, "Rosco", LocalDate.now());
        createAndSaveOwner(2L, "Fiona", "Glenanne", "45 Brickerel", "Miami", "123456790", savedCatType, "Garfield", LocalDate.now());
        System.out.println("Loaded Owners....");

        Vet vet1 = new Vet();
        vet1.setId(1L);
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setId(2L);
        vet2.setFirstName("Jessie");
        vet2.setLastName("Porter");
        vetService.save(vet2);

        System.out.println("Loaded Vets....");
    }

    private void createAndSaveOwner(Long id, String firstName, String lastName, String address, String city, String telephone, PetType petType, String petName, LocalDate birthDate) {
        Owner owner = new Owner();
        owner.setId(id);
        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        owner.setAddress(address);
        owner.setCity(city);
        owner.setTelephone(telephone);

        Pet pet = new Pet();
        pet.setPetType(petType);
        pet.setOwner(owner);
        pet.setBirthDate(birthDate);
        pet.setName(petName);
        owner.getPets().add(pet);

        ownerService.save(owner);
    }
}
