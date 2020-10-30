# SISO CUSTOMER API 
## Kotlin RESTful Spring Boot API

## Migrations 

### Info command
```
./gradlew flywayInfo -Dflyway.url=jdbc:postgresql://localhost:5432/siso -Dflyway.user=postgres -Dflyway.password=123456
```

### Migrate command
```
./gradlew flywayMigrate -Dflyway.url=jdbc:postgresql://localhost:5432/siso -Dflyway.user=postgres -Dflyway.password=123456
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
  "status": "CREATED",
  "data" : {
      "id": "5e2cf612-4ba6-424f-9cf9-a07e76ebbbb0",
      "name": "Customer",
      "email": "customer@email.com",
      "mobile_phone": "554499999-9999",
      "phone": "55449999-9999",
      "birth_date": "1990-01-01",
      "document": "111.222.333-44",
      "created_at": "",
      "updated_at": ""
  }
}
```
