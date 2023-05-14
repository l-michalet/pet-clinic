package micha.udemy.petclinic.service.map;

import micha.udemy.petclinic.model.PetType;
import micha.udemy.petclinic.service.PetTypeService;

import java.util.Set;

public class PetTypeMapService extends AbstractMapService<PetType, Long> implements PetTypeService {

    @Override
    public Set<PetType> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(PetType petType) {
        super.delete(petType);
    }

    @Override
    public PetType save(PetType petType) {
        return save(petType);
    }

    @Override
    public PetType findById(Long id) {
        return findById(id);
    }
}
