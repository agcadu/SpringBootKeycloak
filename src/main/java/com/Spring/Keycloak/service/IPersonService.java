package com.Spring.Keycloak.service;

import com.Spring.Keycloak.model.PersonEntity;
import com.Spring.Keycloak.response.PersonResponseRest;
import org.springframework.http.ResponseEntity;

public interface IPersonService {

    public ResponseEntity<PersonResponseRest> search();

    public ResponseEntity<PersonResponseRest> save(PersonEntity person);

    public ResponseEntity<PersonResponseRest> deleteById(Long id);
}
