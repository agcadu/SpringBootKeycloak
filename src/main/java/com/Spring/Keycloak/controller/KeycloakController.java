package com.Spring.Keycloak.controller;

import com.Spring.Keycloak.dto.UserDTO;
import com.Spring.Keycloak.service.IKeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/keycloak/user")
@PreAuthorize("hasRole('admin_client_role')")
public class KeycloakController {

    @Autowired
    private IKeycloakService keycloakService;

    @GetMapping("/search")
    public ResponseEntity findAllUsers() {
        return ResponseEntity.ok(keycloakService.findAllUsers());
    }

    @GetMapping("/search/{username}")
    public ResponseEntity searchUser(@PathVariable String username) {
        return ResponseEntity.ok(keycloakService.searchUser(username));
    }

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody UserDTO user) throws URISyntaxException {
        String response = keycloakService.createUser(user);
        return ResponseEntity.created(new URI("/keycloak/user")).body(response);

    }

    @PutMapping("/update/{userId}")
    public ResponseEntity updateUser(@PathVariable String userId, @RequestBody UserDTO user) {
        keycloakService.updateUser(userId, user);
        return ResponseEntity.ok("User updated successfully");
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable String userId) {
        keycloakService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }


}
