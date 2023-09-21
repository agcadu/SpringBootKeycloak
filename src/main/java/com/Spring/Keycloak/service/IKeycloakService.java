package com.Spring.Keycloak.service;

import com.Spring.Keycloak.dto.UserDTO;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface IKeycloakService {

    List<UserRepresentation>findAllUsers();
    List<UserRepresentation>searchUser(String username);
    String createUser(UserDTO userDTO);
    void deleteUser(String userId);
    void updateUser(String userId, UserDTO userDTO);

}
