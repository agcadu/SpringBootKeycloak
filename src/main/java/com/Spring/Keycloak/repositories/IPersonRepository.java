package com.Spring.Keycloak.repositories;

import com.Spring.Keycloak.model.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonRepository extends CrudRepository<PersonEntity, Long> {
}