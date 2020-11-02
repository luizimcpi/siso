# SISO CUSTOMER API 
## Kotlin RESTful Spring Boot API
[![Build Status](https://travis-ci.org/luizimcpi/siso.svg?branch=master)](https://travis-ci.org/luizimcpi/siso)


## Migrations 
IMPORTANT: Migrations is running automatically when application starts. If you want disable it just comment
the following line in build.gradle file and run "Migrate Command"
```
implementation("org.flywaydb:flyway-core:6.4.1")
```

### Info command
```
./gradlew flywayInfo -Dflyway.url=jdbc:postgresql://localhost:5432/siso -Dflyway.user=postgres -Dflyway.password=123456
```

### Migrate command
```
./gradlew flywayMigrate -Dflyway.url=jdbc:postgresql://localhost:5432/siso -Dflyway.user=postgres -Dflyway.password=123456
```

## Run Configurations
#### ENV VARS
```
AUTH_SECRET_KEY=GmpGT97dooIJGBsknf30uhYLxveSBCFM
```

## Health Check
#### Request
- Method: GET
- Endpoint: /api/actuator/health

## Create Customer 
#### Request
- Method: POST
- Endpoint: /api/customers
- Header:
    - Authorization: Bearer Auth Token
    - Accept: application/json
- Body
```json
{
  "name": "Customer",
  "email": "customer@email.com",
  "mobile_phone": "554499999-9999",
  "phone": "55449999-9999",
  "birth_date": "1990-01-01",
  "document": "111.222.333-44" 
}
```

#### Response ( 201 Created)
```json
{
    "code": 201,
    "status": "201 CREATED",
    "data": {
        "id": "03e14143-331f-436d-9104-6a9297f92464",
        "user_id": 1,
        "name": "Customer",
        "email": "customer@email.com",
        "mobile_phone": "554499999-9999",
        "phone": "55449999-9999",
        "birth_date": "1990-01-01",
        "document": "111.222.333-44",
        "created_at": "2020-10-30T18:20:50.469",
        "updated_at": "2020-10-30T18:20:50.47"
    }
}
```
## Swagger
```
http://localhost:8080/api/swagger-ui.html
```