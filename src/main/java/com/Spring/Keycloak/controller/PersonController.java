package com.Spring.Keycloak.controller;

import com.Spring.Keycloak.model.PersonEntity;
import com.Spring.Keycloak.response.PersonResponseRest;
import com.Spring.Keycloak.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/keycloak/person")
@PreAuthorize("hasRole('user_client_role') or hasRole('admin_client_role')")
public class PersonController {

    @Autowired
    private IPersonService personService;


    @GetMapping("/search")
    public ResponseEntity<PersonResponseRest> search(){

        ResponseEntity<PersonResponseRest> response = personService.search();
        return response;
    }

    @PostMapping("/savePerson")
    public ResponseEntity<PersonResponseRest> save(@RequestBody PersonEntity person){

        ResponseEntity<PersonResponseRest> response = personService.save(person);
        return response;
    }

    @DeleteMapping("/deletePerson")
    public ResponseEntity<PersonResponseRest> deleteById(@RequestParam Long id){

        ResponseEntity<PersonResponseRest> response = personService.deleteById(id);
        return response;
    }
}