package com.Spring.Keycloak.util;

import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;

public class KeycloakProvider {

    private static final String KEYCLOAK_SERVER_URL = "http://localhost:9090";
    private static final String REALM_NAME = "spring-boot-realm-dev";
    private static final String REALM_MASTER = "master";
    private static final String ADMIN_CLI = "admin-cli";
    private static final String USER_CONSOLE = "admin";
    private static final String CONSOLE_PASSWORD = "admin";
    private static final String CLIENT_SECRET = "ImkALWxAYqQOzg9SL2cJ1nfrKyGhcRZ7";

    public static RealmResource getRealmResource() {
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


    public static UsersResource getUserResource() {
       RealmResource realmResource = getRealmResource();

       return realmResource.users();
    }

}
