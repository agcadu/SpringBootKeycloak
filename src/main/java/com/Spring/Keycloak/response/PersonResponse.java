package com.Spring.Keycloak.response;

import com.Spring.Keycloak.model.PersonEntity;
import lombok.Data;

import java.util.List;

@Data
public class PersonResponse {

    private List<PersonEntity> person;
}
