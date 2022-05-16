# Development set up

### Requisites

The following software is required:

- Docker with `docker compose`
- Java JDK 17+

### Start the required services

Run the development Docker compose to spin up a Postgres database and Minio
container

```shell
docker compose -f docker-compose-dev.yml up -d
```

### Run the Spring application

```shell
./mvnw spring-boot:run
```