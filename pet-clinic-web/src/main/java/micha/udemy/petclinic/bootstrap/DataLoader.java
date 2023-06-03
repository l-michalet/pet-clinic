package micha.udemy.petclinic.bootstrap;

import micha.udemy.petclinic.model.Owner;
import micha.udemy.petclinic.model.Pet;
import micha.udemy.petclinic.model.PetType;
import micha.udemy.petclinic.model.Specialty;
import micha.udemy.petclinic.model.Vet;
import micha.udemy.petclinic.service.OwnerService;
import micha.udemy.petclinic.service.PetTypeService;
import micha.udemy.petclinic.service.SpecialtyService;
import micha.udemy.petclinic.service.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadOwnersData();
            loadVetsData();
        }
    }

    private void loadOwnersData() {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType savedDogType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType savedCatType = petTypeService.save(cat);

        createAndSaveOwner(1L, "Michael", "Weston", "123 Brickerel", "Miami", "123456789", savedDogType, "Rosco", LocalDate.now());
        createAndSaveOwner(2L, "Fiona", "Glenanne", "45 Brickerel", "Miami", "123456790", savedCatType, "Garfield", LocalDate.now());
        System.out.println("Loaded Owners....");
    }

    private void loadVetsData() {
        Specialty radiology = new Specialty();
        radiology.setDescription("Radiology");
        specialtyService.save(radiology);

        Specialty surgery = new Specialty();
        surgery.setDescription("Surgery");
        specialtyService.save(surgery);

        Specialty dentistry = new Specialty();
        dentistry.setDescription("Dentistry");
        specialtyService.save(dentistry);

        createAndSaveVet(1L, "Sam", "Axe", Set.of(radiology));
        createAndSaveVet(2L, "Jessie", "Porter", Set.of(surgery, dentistry));
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

    private void createAndSaveVet(Long id, String firstName, String lastName, Set<Specialty> specialties) {
        Vet vet = new Vet();
        vet.setId(id);
        vet.setFirstName(firstName);
        vet.setLastName(lastName);
        vet.setSpecialties(specialties);
        vetService.save(vet);
    }
}
