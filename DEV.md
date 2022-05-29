# How to create a development environment for `Ubay`

## Required software

- Docker with `docker compose` installed
- Java JDK 17 or later
- Maven

## Creating the development environment
Run the development Docker compose to spin up a Postgres database and Minio
container

```shell
git clone https://github.com/Altair-Bueno/ubay-spring
cd ubay-spring
docker compose -f docker-compose-dev.yml up -d
# To stop the server
docker compose -f docker-compose-dev.yml down
```

## Running `Ubay`

This project uses `mvnw` to start the application.

```shell
# Inside the project root directory
./mvnw spring-boot:run
```