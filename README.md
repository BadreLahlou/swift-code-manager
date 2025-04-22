# Swift Code Manager

A Robust REST API for managing SWIFT (BIC) codes, designed for reliability, clarity, and performance. Built with Java 17 and Spring Boot 3.4.4, it features robust validation, advanced caching, OpenAPI documentation, and seamless containerization for effortless deployment.

## Features
- Automated SWIFT Code Import from CSV on startup
- RESTful API: Retrieve, add, and delete SWIFT codes
- Advanced validation and error handling
- Fast queries by SWIFT code and country
- OpenAPI/Swagger UI for easy testing
- Unit and integration tests
- Dockerized app and database

## Project Structure (Key Parts)
- `src/main/java/com/swift/manager/controller/` — REST API controllers
- `src/main/java/com/swift/manager/service/` — Business logic and data handling
- `src/main/java/com/swift/manager/entity/` — JPA entities (database models)
- `src/main/java/com/swift/manager/dto/` — Data Transfer Objects (API request/response models)
- `src/main/java/com/swift/manager/validation/` — Custom validation (e.g., SWIFT code format)
- `src/main/java/com/swift/manager/exception/` — Global error handling
- `src/main/resources/data/swift_codes.csv` — Initial SWIFT data loaded on startup
- `src/test/java/` — Unit and integration tests
- `Dockerfile`, `docker-compose.yml` — Containerization for app and database

## How It Works (Crucial Parts)
- **CSV Parsing:** On startup, the app loads and parses `swift_codes.csv` if the database is empty.
- **Data Storage:** Data is stored in PostgreSQL, with fast queries by SWIFT code and country.
- **API Endpoints:**
  - `GET /v1/swift-codes/{swift-code}` — Get details for a SWIFT code (headquarter or branch)
  - `GET /v1/swift-codes/country/{countryISO2}` — Get all SWIFT codes for a country
  - `POST /v1/swift-codes` — Add a new SWIFT code
  - `DELETE /v1/swift-codes/{swift-code}` — Delete a SWIFT code
- **Validation:** All input is validated (e.g., SWIFT code format, required fields)
- **Error Handling:** All errors return clear, structured JSON responses
- **Testing:** Unit and integration tests ensure reliability and edge case coverage
- **Swagger UI:** Interactive API docs and testing at `/swagger-ui.html`
- **Containerization:** App and DB run together with Docker Compose

---

## Setup: Run Locally (No Docker)
1. **Clone the repository:**
   ```
   git clone https://github.com/your-username/your-repo-name.git
   cd your-repo-name
   ```
2. **Install PostgreSQL** and create a database:
   - DB name: `swiftdb`
   - User: `swiftuser` Password: `swiftpass`
3. **Update `src/main/resources/application.yml`** if your DB settings differ.
4. **Build the project:**
   ```
   mvn clean package
   ```
5. **Run the app:**
   ```
   java -jar target/swift-code-manager-1.0.0-SNAPSHOT.jar
   ```
6. **Access the API docs:**
   - [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## Setup: Run with Docker (Recommended)
1. **Clone the repository:**
   ```
   git clone https://github.com/your-username/your-repo-name.git
   cd your-repo-name
   ```
2. **Build the app:**
   ```
   mvn clean package
   ```
3. **Start everything:**
   ```
   docker-compose up --build
   ```
4. **Access the API docs:**
   - [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## How to Use the API 
- Open [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) in your browser.
- Expand any endpoint, click **Try it out**, fill in parameters, and click **Execute** to see the response.
- Example requests and responses are shown above in the README.

---

## How to Run All Tests
```
mvn test
```

---

## How to Stop the App
Press `Ctrl+C` in the terminal, then run:
```
docker-compose down
```

---

## Notes
- All API responses use uppercase for country codes and names.
- You can use Swagger UI for all API testing—no frontend needed!


