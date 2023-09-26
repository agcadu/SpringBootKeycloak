![Encabezado del Proyecto](./header.png)

# SpringBootKeycloak

SpringBootKeycloak es una aplicación desarrollada con Spring Boot 3, Java 17 y Maven. Esta aplicación integra Keycloak con Spring para proporcionar autenticación y autorización a través de JWT (JSON Web Tokens) y Spring Security.

### Beneficios de utilizar Keycloak con JWT y Spring Security:

1. **Seguridad Centralizada**: Keycloak proporciona una solución centralizada para la autenticación y autorización, lo que facilita la gestión de usuarios y permisos.
2. **Estándar de la Industria**: JWT es un estándar abierto (RFC 7519) que define una forma compacta y autónoma de transmitir información entre partes como un objeto JSON. Esto permite la autenticación de usuarios y la transmisión de información de autorización.
3. **Integración con Spring Security**: Spring Security proporciona características de seguridad robustas y se integra perfectamente con JWT y Keycloak, permitiendo una gestión detallada de los roles y permisos de los usuarios.
4. **Escalabilidad**: Al separar la autenticación y autorización en un servicio como Keycloak, las aplicaciones pueden escalar sin preocuparse por la gestión de usuarios.
5. **Soporte para múltiples protocolos**: Keycloak soporta protocolos estándar como OpenID Connect y SAML, lo que facilita su integración con diferentes aplicaciones y servicios.

### Configuración:

- **Realm**: `spring-boot-realm-dev`
- **Cliente**: `spring-client-api-rest`
- **Roles**: `admin_client_role`, `user_client_role`
- **Usuario Admin**: `pepe.perez` con contraseña `1234`
- **Usuario User**: `sara.baras` con contraseña `1234`

### Endpoints:

1. **Obtener Token**:
    - POST `http://localhost:9090/realms/spring-boot-realm-dev/protocol/openid-connect/token`
2. **Gestión de Usuarios (Keycloak)**:
    - GET `http://localhost:9090/admin/realms/spring-boot-realm-dev/users`
3. **Gestión de Usuarios (Aplicación)**:
    - GET `http://localhost:8080/keycloak/user/search`
    - GET `http://localhost:8080/keycloak/user/search/{user}`
    - POST `http://localhost:8080/keycloak/user/create`
    - PUT `http://localhost:8080/keycloak/user/update/{id}`
    - DELETE `http://localhost:8080/keycloak/user/delete/{id}`
4. **Gestión de Datos de Personas**:
    - GET `http://localhost:8080/keycloak/person/search`
    - POST `http://localhost:8080/keycloak/person/savePerson`
    - DELETE `http://localhost:8080/keycloak/person/deletePerson/{id}`

### Dockerización:

La aplicación está dockerizada y se compone de tres contenedores:

1. Servidor Keycloak
2. Base de datos PostgreSQL
3. Aplicación SpringBootKeycloak

Estos contenedores están definidos y gestionados mediante un archivo `docker-compose`.

### Archivos de Configuración:

- Configuración del servidor Keycloak: `exported_keycloak_config.json`
- Colección Postman para pruebas: `ApplicationKeycloak.postman_collection.json`



### Base de Datos:

`jdbc:postgresql://localhost:5432/spring_keycloak`

### Instrucciones para la Instalación:

1. Clonar el repositorio:
   ```bash
   git clone https://github.com/agcadu/SpringBootKeycloak.git
   ```
2. Navegar al directorio del proyecto:
   ```bash
   cd SpringBootKeycloak
   ```
3. Levantar los contenedores con Docker Compose:
   ```bash
   docker compose up 
   ```
4. Una vez que todos los servicios estén en funcionamiento, puedes acceder a la aplicación y comenzar a realizar pruebas.

### Nota:

Este es un entorno de prueba. Por razones de seguridad, en un entorno de producción, no se deben exponer secretos, contraseñas u otra información sensible. Es crucial seguir las mejores prácticas de seguridad al desplegar aplicaciones en entornos de producción.
