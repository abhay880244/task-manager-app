# Task Manager Application

A Spring Boot REST API for managing tasks, built with Kotlin.

## Features

- Create, read, update, and delete tasks
- Set task priorities (LOW, MEDIUM, HIGH)
- Filter high-priority tasks
- Optimistic locking with versioning
- Validation on task input

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/tasks` | Get all tasks |
| GET | `/api/tasks/{id}` | Get a task by ID |
| POST | `/api/tasks` | Create a new task |
| PUT | `/api/tasks/{id}` | Update a task |
| DELETE | `/api/tasks/{id}` | Delete a task |
| GET | `/api/tasks/high-priority` | Get all high-priority tasks |

## Task Model

```json
{
  "id": 1,
  "version": 1,
  "title": "Complete project",
  "description": "Finish the task manager app",
  "priority": "HIGH",
  "completed": false,
  "createdAt": "2024-01-15T10:30:00",
  "updatedAt": "2024-01-15T10:30:00"
}
```

## Request Body

When creating or updating a task, omit `id`, `version`, `createdAt`, and `updatedAt`:

```json
{
  "title": "Complete project",
  "description": "Finish the task manager app",
  "priority": "HIGH",
  "completed": false
}
```

- `title` is required (cannot be blank)
- `priority` defaults to `MEDIUM` if not specified
- `description`, `priority`, and `completed` are optional

## Requirements

- Java 21
- Gradle (via Gradle Wrapper)

## Running the Application

```bash
./gradlew bootRun
```

The API will be available at `http://localhost:8080`.

## Database Configuration

The application uses PostgreSQL. The database is configured in `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/taskmanager
spring.datasource.username=abhaykaushal
spring.datasource.password=${DB_PASSWORD:}
spring.jpa.hibernate.ddl-auto=update
```

Set the `DB_PASSWORD` environment variable if authentication is required.

## Testing

Run tests with:

```bash
./gradlew test
```

## Project Structure

```
src/
├── main/kotlin/com/task/manager/taskmanagerapp/
│   ├── TaskManagerAppApplication.kt    # Main entry point
│   ├── controller/
│   │   ├── TaskController.kt           # REST endpoints
│   │   └── GlobalExceptionHandler.kt   # Error handling
│   ├── model/
│   │   ├── Task.kt                     # Entity
│   │   ├── TaskRequest.kt              # Request DTO
│   │   ├── TaskResponse.kt             # Response DTO
│   │   └── Priority.kt                 # Enum (LOW, MEDIUM, HIGH)
│   ├── repository/
│   │   └── TaskRepository.kt           # Data access layer
│   └── service/
│       └── TaskService.kt              # Business logic
└── test/kotlin/                        # Test classes
```

## Error Handling

The API returns meaningful error responses:

- `400 Bad Request` - Validation errors or constraint violations
- `404 Not Found` - Task not found
- `409 Conflict` - Optimistic locking failure (concurrent modification)