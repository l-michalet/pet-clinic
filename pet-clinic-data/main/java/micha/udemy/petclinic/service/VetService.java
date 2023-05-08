package micha.udemy.petclinic.service;

import micha.udemy.petclinic.model.Owner;
import micha.udemy.petclinic.model.Vet;

import java.util.Set;

public interface VetService {

    Vet findById(Long id);

    Vet save(Owner owner);

    Set<Vet> findAll();

}
