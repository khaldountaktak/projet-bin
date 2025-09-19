# ---- Build stage ----
FROM maven:3.9-eclipse-temurin-11 AS build
WORKDIR /w


COPY pom.xml ./
COPY calculator-api/pom.xml calculator-api/pom.xml
COPY calculator-bean/pom.xml calculator-bean/pom.xml
COPY calculator-client/pom.xml calculator-client/pom.xml


RUN mvn -q -DskipTests dependency:go-offline


COPY calculator-api/ calculator-api/
COPY calculator-bean/ calculator-bean/


RUN mvn -q -DskipTests -pl calculator-api,calculator-bean -am clean install


# ---- GlassFish runtime ----
FROM ghcr.io/eclipse-ee4j/glassfish:7.0.23
ENV GLASSFISH_HOME=/opt/glassfish7


COPY --from=build /w/calculator-bean/target/calculator-bean-1.0-SNAPSHOT.jar \
  ${GLASSFISH_HOME}/glassfish/domains/domain1/autodeploy/

EXPOSE 8080 4848 3700


CMD ["asadmin", "start-domain", "-v", "domain1"]
