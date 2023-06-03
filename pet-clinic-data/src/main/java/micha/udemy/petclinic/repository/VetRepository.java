package micha.udemy.petclinic.repository;

import micha.udemy.petclinic.model.Vet;
import org.springframework.data.repository.CrudRepository;

public interface VetRepository  extends CrudRepository<Vet, Long> {
}
