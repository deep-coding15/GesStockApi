# Copilot Instructions for GesStockApi

## Overview
GesStockApi is a **REST API** developed with **Spring Boot 3** for managing products, categories, stocks, and users in a commerce system. The architecture is modular and follows best practices for REST and Spring.

## Architecture
- **Modules**:
  - **Catalogue**: Manages categories and products.
  - **Stock**: Handles stock levels and movements.
  - **Security**: Manages users and roles.
  - **Health**: Monitors API health.

- **Data Flow**: Each stock modification updates the current quantity and generates a stock movement, traceable by product, stock, and user.

## Developer Workflows
- **Building**: Use Maven to build the project. Run `mvn clean install` to compile and package the application.
- **Testing**: Tests are defined in Postman. Ensure to run the complete Postman collection to validate all CRUD endpoints.
- **Debugging**: Use your IDE's debugging tools to set breakpoints in the service classes located in `src/main/java/com/deep_coding15/GesStockApi/`. 

## Project Conventions
- **Naming**: Follow Java naming conventions for classes and methods. DTOs are suffixed with `DTO` (e.g., `CategorieCreateRequestDTO`).
- **Error Handling**: Custom exceptions are defined in the `common/exceptions` package. Use these for consistent error responses.

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
