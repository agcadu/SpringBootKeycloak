FROM openjdk:17-jdk-alpine

RUN mkdir /files

COPY target/SpringBootKeycloak-0.0.1-SNAPSHOT.jar Spring_Boot_Keycloak.jar

RUN chmod -R 777 /files

WORKDIR /

ENTRYPOINT ["java", "-jar", "/Spring_Boot_Keycloak.jar"]