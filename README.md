
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


# Problem Solving Case
From the question, it seems there is a rule in the database to stop two different types of time-off from overlapping for the same employee. This kind of rule can be created using an exclusion constraint in databases like PostgreSQL. Exclusion constraints work well to ensure that no two requests overlap, for example, making sure "Sick Leave" and "Annual Leave" don't happen on the same dates for the same employee.

However, exclusion constraints have a limitation: they treat all cases the same way. They don't allow for exceptions, which means they can't directly handle special cases like allowing "Annual Leave" to overlap with "Work Remotely."

To make this exception work, where "Annual Leave" can overlap with "Work Remotely," we can use other solutions:

1. **Database Trigger:** We can use a database trigger to check the overlap rules in more detail. This trigger would run before saving or updating a time-off request. It would check if there are any overlaps and only allow them if one of the categories is "Work Remotely" and the other is "Annual Leave." This lets us enforce the rule directly in the database while allowing the specific exception.


2. **Application Layer Validation:** Another way is to add this rule to the application itself. Before saving any time-off request to the database, the application can check if the new request overlaps with an existing one and only allow it if it's an acceptable overlap. This gives immediate feedback to the user when they try to make a request.

**Best Practice:** The best solution is to use both methods together. Using a trigger in the database makes sure the rule is always enforced, even if something goes wrong in the application. Adding the check in the application layer also improves the user experience by catching errors early on.


