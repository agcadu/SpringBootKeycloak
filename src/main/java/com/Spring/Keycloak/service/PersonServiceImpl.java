package com.Spring.Keycloak.service;

import com.Spring.Keycloak.model.PersonEntity;
import com.Spring.Keycloak.repositories.IPersonRepository;
import com.Spring.Keycloak.response.PersonResponseRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServiceImpl implements IPersonService{

    @Autowired
    private IPersonRepository personRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<PersonResponseRest> search() {

        PersonResponseRest response = new PersonResponseRest();

        try {

            List<PersonEntity> person = (List<PersonEntity>) personRepository.findAll();

            response.getPersonResponse().setPerson(person);
            response.setMetadata("successful response", "00", "data found");

        } catch (Exception e) {

            response.setMetadata("unsuccessful response", "-1", "Error search");
            e.getStackTrace();
            return new ResponseEntity<PersonResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return new ResponseEntity<PersonResponseRest> (response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<PersonResponseRest> save(PersonEntity person) {

        PersonResponseRest response = new PersonResponseRest();
        List<PersonEntity> list = new ArrayList<>();

        try{

            PersonEntity personEntity = personRepository.save(person);

            if (personEntity != null){
                list.add(personEntity);

                response.getPersonResponse().setPerson(list);
                response.setMetadata("successful response", "00", "Save successful");
            }else {
                response.setMetadata("unsuccessful response", "-1", "Error save");
                return new ResponseEntity<PersonResponseRest>( response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

        } catch (Exception e) {

            response.setMetadata("unsuccessful response", "-1", "Error save");
            e.getStackTrace();
            return new ResponseEntity<PersonResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        return new ResponseEntity<PersonResponseRest>(response, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<PersonResponseRest> deleteById(Long id) {
        PersonResponseRest response = new PersonResponseRest();

        try {
            personRepository.deleteById(id);
            response.setMetadata("successful response", "00", "Delete successful");

        } catch (Exception e) {
            e.printStackTrace();
            response.setMetadata("unsuccessful response", "-1", "Error delete");
            return new ResponseEntity<PersonResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<PersonResponseRest>(response, HttpStatus.OK);
    }
}
