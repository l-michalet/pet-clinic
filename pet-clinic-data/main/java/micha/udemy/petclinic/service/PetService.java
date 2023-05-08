package micha.udemy.petclinic.service;

import micha.udemy.petclinic.model.Owner;
import micha.udemy.petclinic.model.Pet;

import java.util.Set;

public interface PetService {

    Pet findById(Long id);

    Pet save(Owner owner);

    Set<Pet> findAll();

}
