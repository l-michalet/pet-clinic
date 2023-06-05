package map;

import micha.udemy.petclinic.model.Owner;
import micha.udemy.petclinic.service.map.OwnerMapService;
import micha.udemy.petclinic.service.map.PetMapService;
import micha.udemy.petclinic.service.map.PetTypeMapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

    OwnerMapService ownerMapService;

    private static final Long OWNER_ID = 1L;
    private static final String OWNER_NAME = "Smith";
    private Owner firstOwner;

    @BeforeEach
    public void setUp() {
        ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
        firstOwner = ownerMapService.save(Owner.builder().id(OWNER_ID).lastName(OWNER_NAME).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerMapService.findAll();
        assertEquals(1, ownerSet.size());
    }

    @Test
    void deleteById() {
        ownerMapService.deleteById(OWNER_ID);
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void delete() {
        ownerMapService.delete(firstOwner);
        assertEquals(0, ownerMapService.findAll().size());
    }

    @Test
    void save() {
        Owner savedOwner = ownerMapService.save(Owner.builder().id(OWNER_ID).build());
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void findById() {
        Owner owner = ownerMapService.findById(OWNER_ID);
        assertEquals(OWNER_ID, owner.getId());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerMapService.findByLastName(OWNER_NAME);
        assertEquals(OWNER_ID, owner.getId());
    }

    @Test
    void findByLastName_notFound() {
        Owner owner = ownerMapService.findByLastName("WRONG_NAME");
        assertNull(owner);
    }
}