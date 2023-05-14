package micha.udemy.petclinic.service;

import micha.udemy.petclinic.model.Owner;


public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

}