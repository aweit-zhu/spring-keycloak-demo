version: '3.8'

services:
  postgres:
    image: postgres:15
    environment:
      POSTGRES_DB: keycloak
      POSTGRES_USER: keycloak
      POSTGRES_PASSWORD: password
    ports:
      - "5432:5432"
    volumes:
      - pgdata:/var/lib/postgresql/data

  ldap:
    image: osixia/openldap:1.5.0
    container_name: ldap
    environment:
      LDAP_ORGANISATION: "Example Org"
      LDAP_DOMAIN: "example.org"
      LDAP_ADMIN_PASSWORD: admin
    ports:
      - "389:389"
    volumes:
      - ldapdata:/var/lib/ldap
      - ldapconfig:/etc/ldap/slapd.d
    depends_on:
      - postgres
    restart: always

  phpldapadmin:
    image: osixia/phpldapadmin:0.9.0
    container_name: phpldapadmin
    environment:
      PHPLDAPADMIN_LDAP_HOSTS: ldap
      PHPLDAPADMIN_HTTPS: "false"
    ports:
      - "8081:80"
    depends_on:
      - ldap
    restart: always

  keycloak:
    image: quay.io/keycloak/keycloak:24.0.4
    container_name: keycloak
    environment:
      KC_DB: postgres
      KC_DB_URL_HOST: postgres
      KC_DB_USERNAME: keycloak
      KC_DB_PASSWORD: password
      KEYCLOAK_ADMIN: admin
      KEYCLOAK_ADMIN_PASSWORD: admin
    ports:
      - "8080:8080"
    command: start-dev
    depends_on:
      - ldap
      - postgres
    restart: always

  pgadmin:
    image: dpage/pgadmin4:7.0
    container_name: pgadmin
    environment:
      PGADMIN_DEFAULT_EMAIL: admin@example.com
      PGADMIN_DEFAULT_PASSWORD: admin
    ports:
      - "8082:80"
    depends_on:
      - postgres
    restart: always

volumes:
  ldapdata:
  ldapconfig:
  pgdata:
