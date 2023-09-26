package com.Spring.Keycloak.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonResponseRest extends ResponseRest{

    private PersonResponse personResponse = new PersonResponse();
}