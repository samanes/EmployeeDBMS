
# Employee Database Management System

This project is an Employee Database Management System (DBMS) implemented in Java. It includes an in-memory database to manage employee records using a repository pattern. The project demonstrates the use of object-oriented design principles, thread safety with `ConcurrentHashMap`, and utility classes for common functionalities.

## Features

- **Employee Management**: Allows adding, updating, retrieving, and deleting employee records.
- **Thread-Safe Repository**: Uses `ConcurrentHashMap` to ensure thread-safe operations.
- **Data Validation**: Includes validation for email addresses using the `EmailValidator` utility class.
- **Timezone Conversion**: Converts timestamps between server and local timezones.
- **Unit Tests**: Comprehensive unit tests to validate the functionality and thread safety of the system.

## Project Structure

- `model` - Contains the `Employee` class representing the employee entity.
- `repository` - Includes the `CrudRepository` interface and `InMemoryEmployeeDatabase` class.
- `util` - Contains utility classes like `DateTimeUtil` and `EmailValidator`.
- `exception` - Custom exceptions for error handling in the repository.
- `test` - Unit tests for the classes using JUnit.

## Usage

### Employee Management

The `Employee` class is an immutable representation of an employee. You can use the builder pattern to create instances of `Employee`.

Example:
```java
Employee employee = Employee.builder()
    .employeeId(UUID.randomUUID())
    .name("John Doe")
    .position("Developer")
    .email("john.doe@example.com")
    .salary(50000.0)
    .createdAt(ZonedDateTime.now())
    .modifiedAt(ZonedDateTime.now())
    .build();
```

### Repository Operations

`InMemoryEmployeeDatabase` implements `CrudRepository<Employee, UUID>` to provide basic CRUD operations. It uses `ConcurrentHashMap` to store employee data, ensuring thread safety.

Example:
```java
InMemoryEmployeeDatabase database = new InMemoryEmployeeDatabase();
database.add(employee);
Optional<Employee> retrievedEmployee = database.get(employee.getEmployeeId());
```

### Email Validation

`EmailValidator` is a utility class to validate email addresses. It throws an `IllegalArgumentException` if the email format is invalid.

Example:
```java
EmailValidator.validate("test@example.com");
```

### Timezone Conversion

`DateTimeUtil` provides methods to convert `ZonedDateTime` between server timezone (`UTC`) and local timezone.

Example:
```java
ZonedDateTime serverTime = DateTimeUtil.toServerTimezone(ZonedDateTime.now());
ZonedDateTime localTime = DateTimeUtil.toLocalTimezone(serverTime);
```

## Testing

The project includes unit tests for:
- `Employee` class
- `InMemoryEmployeeDatabase`
- `EmailValidator`
- `DateTimeUtil`

To run the tests, use your IDE's test runner or execute the tests using Maven/Gradle.

## Getting Started

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/samanes/EmployeeDBMS.git
   ```
2. **Build the Project**:
   - Using Maven:
     ```bash
     mvn clean install
     ```
3. **Run Tests**:
   ```bash
   mvn test
   ```

## Requirements

- Java 17 or higher
- Maven for building the project

