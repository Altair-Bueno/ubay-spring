# Ubay spring

It's like [ubay](https://github.com/Altair-Bueno/ubay), but written using Spring

## Run the Application

Use Docker compose to run the application

```shell
docker compose up -d
```

---

## Development

### Requisites

- Docker compose
- A Java jdk 17 or later

### Start the required services

```shell
docker compose -f docker-compose-dev.yml up -d
```

### Build and run Ubay Spring

```shell
./mvnw spring-boot:run
```