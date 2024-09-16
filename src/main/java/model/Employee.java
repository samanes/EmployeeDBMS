package model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import util.EmailValidator;

import java.time.ZonedDateTime;
import java.util.UUID;

/**
 * Represents an Employee with immutable fields.
 */
@Getter
@ToString
@EqualsAndHashCode
public class Employee {
    private final UUID employeeId;
    private final String name;
    private final String position;
    private final String email;
    private final double salary;
    private final ZonedDateTime createdAt;
    private final ZonedDateTime modifiedAt;

    /**
     * Constructs a new Employee.
     *
     * @param employeeId the unique identifier for the employee
     * @param name the name of the employee
     * @param position the position of the employee
     * @param email the email of the employee (must be valid)
     * @param salary the salary of the employee
     * @param createdAt the timestamp when the employee was created
     * @param modifiedAt the timestamp when the employee was last modified
     * @throws IllegalArgumentException if the email format is invalid
     */
    @Builder
    public Employee(UUID employeeId, String name, String position, String email, double salary, ZonedDateTime createdAt, ZonedDateTime modifiedAt) {
        EmailValidator.validate(email);
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.email = email;
        this.salary = salary;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
