package com.Spring.Keycloak.util;

import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeycloakProvider {


    @Value("${keycloak.server.url}")
    private String KEYCLOAK_SERVER_URL;

    @Value("${keycloak.realm.name}")
    private String REALM_NAME;

    @Value("${keycloak.realm.master}")
    private String REALM_MASTER;

    @Value("${keycloak.admin.cli}")
    private String ADMIN_CLI;

    @Value("${keycloak.user.console}")
    private String USER_CONSOLE;

    @Value("${keycloak.console.password}")
    private String CONSOLE_PASSWORD;

    @Value("${keycloak.client.secret}")
    private String CLIENT_SECRET;

    public RealmResource getRealmResource() {
        Keycloak keycloak = KeycloakBuilder.builder()
                .serverUrl(KEYCLOAK_SERVER_URL)
                .realm(REALM_MASTER)
                .username(USER_CONSOLE)
                .password(CONSOLE_PASSWORD)
                .clientId(ADMIN_CLI)
                .clientSecret(CLIENT_SECRET)
                .resteasyClient(new ResteasyClientBuilderImpl().connectionPoolSize(10).build())
                .build();

        return keycloak.realm(REALM_NAME);
    }


    public UsersResource getUserResource() {
       RealmResource realmResource = getRealmResource();

       return realmResource.users();
    }

}
