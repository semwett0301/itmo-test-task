# ITMO Test Task – Gazprombank

Spring Boot + Kotlin service backed by PostgreSQL, containerized with Docker and orchestrated via docker-compose.

## Tech stack
- **Backend**: Spring Boot (Kotlin), Gradle
- **Database**: PostgreSQL, Flyway
- **Containers**: Docker, docker-compose
- **API Docs**: springdoc-openapi (Swagger UI)

## What it does
Implements a simple API to manage employees and servers and to query related entities:
- Create/update servers
- Delete employees
- List employees for a company
- List servers for a department

## Quick start (Docker)
1) Copy/ensure env (defaults are embedded in `application.yml` and `docker-compose.yml`):
   - `DATABASE_NAME` (e.g., `itmo_db`)
   - `DATABASE_USERNAME` (e.g., `admin`)
   - `DATABASE_PASSWORD` (e.g., `password`)

2) Build and run:

```bash
docker-compose up -d --build
```

3) Services:
- App: `http://localhost:3000` (container exposes 8080 → host 3000)
- DB: `localhost:5432`

Flyway runs automatically; initial schema and data are created from `src/main/resources/db/migration` and `init.sql`.

## Run locally (without Docker)
Prereqs: Java 17+, PostgreSQL running and accessible.

1) Set env vars or edit `src/main/resources/application.yml`:
   - `DATABASE_HOST` (default `localhost`)
   - `DATABASE_PORT` (default `5432`)
   - `DATABASE_NAME` (default `itmo_db`)
   - `DATABASE_USERNAME` (default `admin`)
   - `DATABASE_PASSWORD` (default `password`)

2) Start the app:

```bash
./gradlew bootRun
```

App listens on `http://localhost:8080` in this mode.

### API documentation (Swagger)
After the app starts:
- Swagger UI: `http://localhost:3000/swagger-ui/index.html` (Docker) or `http://localhost:8080/swagger-ui/index.html` (local)

## REST API

Base path: `/`

- **Create server**
  - Method: `POST`
  - Path: `/servers`
  - Request body (JSON, `ServerRequest`):

```json
{
  "name": "web-01",
  "producer": "Dell",
  "ip": "10.0.0.10",
  "ram": 16,
  "ssd": 256,
  "employee_id": "00000000-0000-0000-0000-000000000001"
}
```

  - Validation: `ram >= 1`, `ssd >= 1`
  - Response: `201/200` empty body on success

- **Update server**
  - Method: `PATCH`
  - Path: `/servers/{id}`
  - Path params: `id` (UUID)
  - Request body: same as Create server
  - Response: `200` empty body on success

- **Delete employee**
  - Method: `DELETE`
  - Path: `/employees/{id}`
  - Path params: `id` (UUID)
  - Response: `200` empty body on success

- **List company employees**
  - Method: `POST`
  - Path: `/companies/{id}/employees`
  - Path params: `id` (UUID)
  - Response: `200` JSON array of `EmployeeResponse`:

```json
[
  { "id": "00000000-0000-0000-0000-000000000001", "name": "Alice" },
  { "id": "00000000-0000-0000-0000-000000000002", "name": "Bob" }
]
```

- **List department servers**
  - Method: `POST`
  - Path: `/departments/{id}/servers`
  - Path params: `id` (UUID)
  - Response: `200` JSON array of `ServerResponse`:

```json
[
  {
    "id": "00000000-0000-0000-0000-0000000000aa",
    "name": "web-01",
    "producer": "Dell",
    "ip": "10.0.0.10",
    "ram": 16,
    "ssd": 256,
    "employee_id": "00000000-0000-0000-0000-000000000001"
  }
]
```

## Data models

- `ServerRequest` / `ServerResponse` fields:
  - `name`: string
  - `producer`: string
  - `ip`: string
  - `ram`: integer (min 1)
  - `ssd`: integer (min 1)
  - `employee_id`: UUID (nullable in response)

- `EmployeeResponse` fields:
  - `id`: UUID
  - `name`: string

## Error handling
- Validation errors: HTTP 400 with field → message map
- Not found: HTTP 404 with error message
- Illegal state/arguments: HTTP 400 with error message

### Notes
- Default CORS is open (`@CrossOrigin("*")`) on controllers.
- OpenAPI group: `base-service` (all paths).


