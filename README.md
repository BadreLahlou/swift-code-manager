# Swift Code Manager

A robust REST API for managing SWIFT codes, built with Java 17 and Spring Boot 3.4.4. The project features advanced validation, caching, OpenAPI documentation, and is fully containerized for easy deployment.

## System Architecture

- **Spring Boot 3.4.4** (Web, Data JPA, Validation)
- **PostgreSQL 15** (via Docker)
- **Ehcache** (JPA & Spring Cache)
- **OpenAPI/Swagger** (API docs)
- **JUnit 5, Mockito, Testcontainers** (Testing)
- **Spring Actuator** (Health, metrics)

## Technology Stack

- Java 17
- Spring Boot
- PostgreSQL
- Docker & Docker Compose
- Maven
- Ehcache
- OpenAPI/Swagger
- JUnit 5, Mockito, Testcontainers

## API Documentation

Interactive OpenAPI docs available at `/swagger-ui.html` when the app is running.

## Local Setup

1. **Clone the repository**
2. **Build the project:**
   ```sh
   mvn clean package
   ```
3. **Run with Docker Compose:**
   ```sh
   docker-compose up --build
   ```
4. **Access API docs:**
   - [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Docker Deployment

- The app and PostgreSQL run as separate containers.
- Data is initialized from `src/main/resources/data/swift_codes.csv` if the DB is empty.

## Testing

- Unit tests: `mvn test`
- Integration tests use Testcontainers for PostgreSQL
- Test config: `src/test/resources/application-test.yml`

## Performance & Caching

- Ehcache is used for both JPA and service-level caching.
- Key queries and entities are indexed and cached for optimal performance.

## Contribution Guidelines

1. Fork the repo and create a feature branch.
2. Write clear, well-tested code.
3. Document your changes.
4. Open a pull request with a detailed description.

## License

MIT License