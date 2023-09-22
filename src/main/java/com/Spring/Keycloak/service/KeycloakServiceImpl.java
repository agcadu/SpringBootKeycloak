package com.Spring.Keycloak.service;

import com.Spring.Keycloak.dto.UserDTO;
import com.Spring.Keycloak.util.KeycloakProvider;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class KeycloakServiceImpl implements IKeycloakService{


    private final KeycloakProvider keycloakProvider;

    public KeycloakServiceImpl(KeycloakProvider keycloakProvider) {
        this.keycloakProvider = keycloakProvider;
    }


    /**
     * This method is used to get all users from Keycloak
     * @return List<UserRepresentation>
     */
    @Override
    public List<UserRepresentation> findAllUsers() {
        return keycloakProvider.getRealmResource().users().list();
    }

    /**
     * This method is used to search users by username
     * @param username
     * @return List<UserRepresentation>
     */
    @Override
    public List<UserRepresentation> searchUser(String username) {
        return keycloakProvider.getRealmResource().users().searchByUsername(username, true);
    }

    /**
     * This method is used to create a new user
     * @param userDTO
     * @return List<UserRepresentation>
     */
    @Override
    public String createUser(@NonNull UserDTO userDTO) {

        int status = 0;
        UsersResource usersResource = keycloakProvider.getUserResource();

        UserRepresentation userRepresentation = new UserRepresentation();

        userRepresentation.setUsername(userDTO.getUsername());
        userRepresentation.setEmail(userDTO.getEmail());
        userRepresentation.setFirstName(userDTO.getFirstName());
        userRepresentation.setLastName(userDTO.getLastName());
        userRepresentation.setEmailVerified(true);
        userRepresentation.setEnabled(true);

        Response response = usersResource.create(userRepresentation);

        status = response.getStatus();

        if (status == 201) {
            log.info("User created successfully");
            String path = response.getLocation().getPath();
            String userId = path.substring(path.lastIndexOf('/') + 1);

            CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
            credentialRepresentation.setTemporary(false);
            credentialRepresentation.setType(OAuth2Constants.PASSWORD);
            credentialRepresentation.setValue(userDTO.getPassword());

            usersResource.get(userId).resetPassword(credentialRepresentation);

            RealmResource realmResource = keycloakProvider.getRealmResource();

            List<RoleRepresentation> roleRepresentations = null;

            if(userDTO.getRoles() == null || userDTO.getRoles().isEmpty()) {
                roleRepresentations = List.of(realmResource.roles().get("user").toRepresentation());
            } else {
                roleRepresentations = realmResource.roles()
                        .list()
                        .stream()
                        .filter(role -> userDTO.getRoles().stream().anyMatch(roleName -> roleName.equalsIgnoreCase(role.getName())))
                        .toList();
            }

            realmResource.users().get(userId).roles().realmLevel().add(roleRepresentations);

            return "User created successfully";
        } else if (status == 409) {
            log.error("User already exists");
            return "User already exists";
        } else {
            log.error("Error creating user");
            return "Error creating user";
        }

    }

    /**
     * This method is used to delete a user
     * @param userId
     * @return void
     */
    @Override
    public void deleteUser(String userId) {
        keycloakProvider.getUserResource().get(userId).remove();
    }

    /**
     * This method is used to update a user
     * @param userId
     * @param userDTO
     * @return void
     */
    @Override
    public void updateUser(String userId,@NonNull UserDTO userDTO) {

        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(OAuth2Constants.PASSWORD);
        credentialRepresentation.setValue(userDTO.getPassword());

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(userDTO.getUsername());
        userRepresentation.setEmail(userDTO.getEmail());
        userRepresentation.setFirstName(userDTO.getFirstName());
        userRepresentation.setLastName(userDTO.getLastName());
        userRepresentation.setEmailVerified(true);
        userRepresentation.setEnabled(true);
        userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));

        UserResource userResource = keycloakProvider.getUserResource().get(userId);
        userResource.update(userRepresentation);

    }
}
