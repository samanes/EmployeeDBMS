package repository;

import exception.EntityAlreadyExistsException;
import exception.EntityNotFoundException;
import model.Employee;
import util.DateTimeUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * An in-memory implementation of the {@link CrudRepository} for managing Employee entities.
 * It uses a {@link ConcurrentHashMap} to store employees, ensuring thread safety.
 */
public class InMemoryEmployeeDatabase implements CrudRepository<Employee, UUID> {
    private final Map<UUID, Employee> employees = new ConcurrentHashMap<>();

    /**
     * Adds an employee to the repository.
     *
     * @param employee the employee to add
     * @throws EntityAlreadyExistsException if the employee already exists
     */
    @Override
    public void add(Employee employee) {
        if (employees.putIfAbsent(employee.getEmployeeId(), employee) != null) {
            throw new EntityAlreadyExistsException("Employee with ID " + employee.getEmployeeId() + " already exists.");
        }
    }

    /**
     * Updates an existing employee in the repository.
     *
     * @param employeeId the identifier of the employee to update
     * @param updatedEmployee the updated employee
     * @throws EntityNotFoundException if the employee is not found
     */
    @Override
    public void update(UUID employeeId, Employee updatedEmployee) {
        boolean updated = employees.computeIfPresent(employeeId, (id, existingEmployee) -> updatedEmployee) != null;
        if (!updated) {
            throw new EntityNotFoundException("Employee with ID " + employeeId + " does not exist.");
        }
    }

    /**
     * Retrieves an employee by their identifier, converting the timestamps to local timezone.
     *
     * @param employeeId the identifier of the employee
     * @return an Optional containing the employee if found, otherwise empty
     */
    @Override
    public Optional<Employee> get(UUID employeeId) {
        return Optional.ofNullable(employees.get(employeeId))
                .map(this::convertToLocalTimezone);
    }

    /**
     * Retrieves all employees in the repository, converting the timestamps to local timezone.
     *
     * @return a collection of all employees
     */
    @Override
    public Collection<Employee> getAll() {
        return employees.values().stream()
                .map(this::convertToLocalTimezone)
                .toList();
    }

    /**
     * Deletes an employee from the repository.
     *
     * @param employeeId the identifier of the employee to delete
     * @throws EntityNotFoundException if the employee is not found
     */
    @Override
    public void delete(UUID employeeId) {
        if (employees.remove(employeeId) == null) {
            throw new EntityNotFoundException("Employee with ID " + employeeId + " not found.");
        }
    }

    /**
     * Converts the employee's timestamps to the local timezone.
     *
     * @param employee the employee whose timestamps to convert
     * @return a new Employee instance with converted timestamps
     */
    private Employee convertToLocalTimezone(Employee employee) {
        return Employee.builder()
                .employeeId(employee.getEmployeeId())
                .name(employee.getName())
                .position(employee.getPosition())
                .email(employee.getEmail())
                .salary(employee.getSalary())
                .createdAt(DateTimeUtil.toLocalTimezone(employee.getCreatedAt()))
                .modifiedAt(DateTimeUtil.toLocalTimezone(employee.getModifiedAt()))
                .build();
    }
}
