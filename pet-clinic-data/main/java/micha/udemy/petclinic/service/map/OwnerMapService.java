package micha.udemy.petclinic.service.map;

import micha.udemy.petclinic.model.Owner;
import micha.udemy.petclinic.service.OwnerService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OwnerMapService extends AbstractMapService<Owner, Long> implements OwnerService {

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public Owner save(Owner object) {
        return super.save(object.getId(), object);
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    public Owner findByLastName(String lastName) {
        return super.findAll().stream()
                .filter(owner -> owner.getLastName().equals(lastName))
                .findFirst()
                .orElse(null);
    }
}