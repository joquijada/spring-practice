# Alaska DOH - Person Registration Demo

A demonstration application for the Alaska Department of Health Integrated Eligibility Platform modernization effort, showcasing the tech stack of the UX and Rules Engine component.

## Overview

This Spring Boot application demonstrates:
- Form-based data entry and submission
- In-memory database persistence using H2
- JPA Repository pattern for CRUD operations
- Mustache template engine for server-side rendering
- RESTful API for AJAX-based operations
- Dynamic UI with JavaScript (no page reloads after initial load)
- Full CRUD functionality (Create, Read, Update, Delete)
- SASS for styling
- Docker containerization

## Features

### Two Versions Available

1. **Server-Side Rendered (SSR) Version** (`/persons`)
   - Traditional form submission with page reload
   - Server-side rendering using Mustache

2. **Dynamic AJAX Version** (`/persons-dynamic`)
   - No page reloads after initial load
   - RESTful API with JSON responses
   - Full CRUD operations (Create, Read, Update, Delete)
   - Inline editing with real-time updates
   - Smooth user experience with loading states and notifications

### Common Features

- **Person Registration Form**: Simple UI to enter name and address
- **Data Persistence**: Records stored in H2 in-memory database
- **Records Display**: Real-time list of all registered persons
- **Responsive Design**: Mobile-friendly interface built with SASS
- **RESTful Architecture**: Clean separation of concerns with MVC pattern

## Tech Stack

- **Framework**: Spring Boot 3.2.2
- **Template Engine**: Mustache
- **Database**: H2 (in-memory)
- **ORM**: Spring Data JPA
- **Styling**: SASS
- **Build Tool**: Gradle
- **Runtime**: Java 21
- **Containerization**: Docker

## Prerequisites

- Java 21 or higher
- Gradle 8.5 or higher
- Node.js 20 or higher (for SASS compilation)
- Docker (optional, for containerization)

## Getting Started

### 1. Install Node Dependencies

```bash
npm install
```

### 2. Build CSS from SASS

```bash
npm run build:css
```

Or watch for changes:

```bash
npm run watch:css
```

### 3. Build the Application

```bash
./gradlew clean build
```

### 4. Run the Application

```bash
./gradlew bootRun
```

Or run the JAR directly:

```bash
java -jar build/libs/mustache-0.0.1-SNAPSHOT.jar
```

The application will start on `http://localhost:8080`

## Accessing the Application

- **Server-Side Rendered Version**: http://localhost:8080/persons
- **Dynamic AJAX Version**: http://localhost:8080/persons-dynamic (Recommended)
- **H2 Console**: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:testdb`
  - Username: `sa`
  - Password: (leave empty)

## Docker

### Using the Docker Control Script (Recommended)

A convenience script is provided to manage the Docker container lifecycle:

```bash
# Start container (rebuilds image from scratch)
./script/docker-control.sh start

# Stop container
./script/docker-control.sh stop

# Restart container (rebuilds image)
./script/docker-control.sh restart

# Check container status
./script/docker-control.sh status

# View container logs
./script/docker-control.sh logs

# Show help
./script/docker-control.sh help
```

The script automatically:
- Compiles SASS before building the Docker image
- Rebuilds the image with `--no-cache` to ensure latest changes are included
- Manages container lifecycle (start, stop, restart)
- Provides colored output and helpful status messages

### Manual Docker Commands

If you prefer to use Docker commands directly:

#### Build Docker Image

```bash
docker build -t alaska-doh-demo .
```

#### Run Docker Container

```bash
docker run -p 8080:8080 alaska-doh-demo
```

Or with a custom name:

```bash
docker run -d --name alaska-doh-demo-container -p 8080:8080 alaska-doh-demo
```

The application will be available at:
- Server-Side Rendered: `http://localhost:8080/persons`
- Dynamic AJAX Version: `http://localhost:8080/persons-dynamic`

## Project Structure

```
mustache/
├── src/
│   ├── main/
│   │   ├── java/com/exsoinn/spring/practice/mustache/
│   │   │   ├── model/
│   │   │   │   ├── Person.java              # JPA Entity
│   │   │   │   └── Greeting.java
│   │   │   ├── repository/
│   │   │   │   └── PersonRepository.java    # JPA Repository
│   │   │   ├── service/
│   │   │   │   └── GreetingService.java
│   │   │   ├── HelloController.java         # Greeting controller
│   │   │   ├── PersonController.java        # Person registration controller
│   │   │   ├── PersonRestController.java    # REST API controller for CRUD
│   │   │   └── MustacheApplication.java     # Main application class
│   │   └── resources/
│   │       ├── static/
│   │       │   ├── css/
│   │       │   │   └── styles.css           # Compiled CSS
│   │       │   └── scss/
│   │       │       └── styles.scss          # SASS source
│   │       ├── templates/
│   │       │   ├── hello.mustache           # Greeting template
│   │       │   ├── persons.mustache         # Person registration template (SSR)
│   │       │   └── persons-dynamic.mustache # Dynamic AJAX template
│   │       └── application.properties       # Spring configuration
│   └── test/
├── script/
│   └── docker-control.sh                     # Docker container management script
├── build.gradle                              # Gradle build configuration
├── package.json                              # Node.js dependencies
├── Dockerfile                                # Docker configuration
└── README.md                                 # This file
```

## API Endpoints

### Person Management (UI Pages)

- `GET /persons` - Display the server-side rendered person registration form
- `GET /persons-dynamic` - Display the dynamic AJAX-based person registration form
- `POST /persons` - Submit a new person registration (SSR version only)
  - Parameters: `name` (String, required), `address` (String, required)

### Person Management (REST API)

- `GET /api/persons` - Get all persons (returns JSON)
- `GET /api/persons/{id}` - Get a specific person by ID (returns JSON)
- `POST /api/persons` - Create a new person (accepts JSON)
  - Request Body: `{"name": "string", "address": "string"}`
  - Returns: Created person with HTTP 201
- `PUT /api/persons/{id}` - Update an existing person (accepts JSON)
  - Request Body: `{"name": "string", "address": "string"}`
  - Returns: Updated person with HTTP 200
- `DELETE /api/persons/{id}` - Delete a person
  - Returns: HTTP 204 No Content on success

### Greeting (Demo Endpoints)

- `GET /` - Default greeting
- `GET /greet?name={name}` - Personalized greeting
- `GET /custom?message={message}&name={name}` - Custom greeting

## Database Schema

### Person Table

| Column  | Type    | Description           |
|---------|---------|-----------------------|
| id      | BIGINT  | Primary key (auto)    |
| name    | VARCHAR | Person's name         |
| address | VARCHAR | Person's address      |

## Development

### Code Formatting

This project uses Spotless for code formatting with Palantir Java Format:

```bash
./gradlew spotlessApply
```

### Running Tests

```bash
./gradlew test
```

## Notes for Demo

This application demonstrates:

1. **Spring Data JPA**: Simplified data access with repository pattern
2. **H2 Database**: Quick setup for development and demos with in-memory storage
3. **Mustache Templating**: Server-side rendering with minimal logic in templates
4. **RESTful API Design**: REST endpoints following HTTP conventions
5. **AJAX & Dynamic UI**: Single-page application behavior without frameworks
6. **Full CRUD Operations**: Complete Create, Read, Update, Delete functionality
7. **SASS**: Modern CSS preprocessing for maintainable styles
8. **Docker Multi-stage Builds**: Optimized containerization with separate build stages
9. **MVC Pattern**: Clean separation between model, view, and controller layers

## Future Enhancements

Potential improvements for production use:
- Implement pagination for large datasets
- Add server-side form validation with detailed error messages
- Switch to persistent database (PostgreSQL, MySQL)
- Add authentication and authorization
- Add integration and E2E tests
- Implement logging and monitoring
- Add API documentation (Swagger/OpenAPI)
- Implement optimistic UI updates
- Add confirmation modals for destructive actions

## License

This is a demonstration project for the Alaska DOH Integrated Eligibility Platform modernization effort.
