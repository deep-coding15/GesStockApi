# Copilot Instructions for GesStockApi

## Overview
GesStockApi is a **REST API** developed with **Spring Boot 3** for managing products, categories, stocks, ventes and users in a commerce system. The architecture is modular and follows best practices for REST and Spring.

## Architecture
- **Modules**:
  - **Catalogue**: Manages categories and products.
  - **Common**: Contains shared utilities, exceptions, and constants.
  - **Config**: Contains application configuration classes.
  - **Vente**: Handles sales and billing.
  - **Stock**: Handles stock levels and movements.
  - **Security**: Manages users and roles.
  - **Health**: Monitors API health.

- **Data Flow**: Each stock modification updates the current quantity and generates a stock movement, traceable by product, stock, and user.
- **Data Flow**: Each sale generates a stock movement, traceable by product, stock, and user.
- **DTOs**: Used for request/response payloads, ensuring separation between API and internal models.

## Developer Workflows
- **Building**: Use Maven to build the project. Run `mvn clean install` to compile and package the application.
- **Testing**: Tests services are located in `src/test/java/` and can be run with `mvn test`. Ensure to run the complete Postman collection to validate all CRUD endpoints.
- **Testing**: Tests are defined in Postman. Ensure to run the complete Postman collection to validate all CRUD endpoints.
- **Running**: Use `mvn spring-boot:run` to start the application. The API will be available at `http://localhost:8088/api/v1/`. Port can be configured in `application.yml` and can change.
- **Debugging**: Use your IDE's debugging tools to set breakpoints in the service classes located in `src/main/java/com/deep_coding15/GesStockApi/`. 

## Project Conventions
- **Naming**: Follow Java naming conventions for classes and methods. DTOs are suffixed with `DTO` (e.g., `CategorieCreateRequestDTO`).
- **Error Handling**: Custom exceptions are defined in the `common/exceptions` package. Use these for consistent error responses.
- **Logging**: Use SLF4J for logging. Log important events and errors at appropriate levels (INFO, DEBUG, ERROR).
- **Validation**: Use Spring's validation annotations (e.g., `@NotNull`, `@Size`) in DTOs to enforce data integrity.
- **Documentation**: Use Swagger/OpenAPI annotations to document API endpoints. Ensure all endpoints have clear descriptions and example payloads.

## Integration Points
- **Database**: H2 is used for in-memory testing. Future plans include MySQL/PostgreSQL for production.
- **External Dependencies**: Spring Data JPA for database interactions, and Postman for API testing.

## Key Files/Directories
- **Main Application**: `src/main/java/com/deep_coding15/GesStockApi/GesStockApiApplication.java`
- **Configuration**: `src/main/resources/application.yml` for application settings.
- **Tests**: Located in `src/test/java/`, with test classes mirroring the main application structure.

## Example Endpoint
```http
PATCH /api/v1/stocks/{id}
```

### Request Body
```json
{
  "delta": 10,
  "typeMouvement": "ENTREE",
  "commentaire": "Réapprovisionnement",
  "utilisateurId": 1
}
```

## Future Enhancements
- JWT Authentication
- MySQL/PostgreSQL integration
- Sales and billing management
- Pagination, sorting, filtering
- Dockerization
