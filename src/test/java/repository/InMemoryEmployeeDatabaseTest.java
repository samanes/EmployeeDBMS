package repository;

import model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import exception.EntityAlreadyExistsException;
import exception.EntityNotFoundException;

import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link InMemoryEmployeeDatabase} class.
 */
class InMemoryEmployeeDatabaseTest {

    private InMemoryEmployeeDatabase database;
    private Employee employee;

    @BeforeEach
    void setUp() {
        database = new InMemoryEmployeeDatabase();
        employee = Employee.builder()
                .employeeId(UUID.randomUUID())
                .name("John Doe")
                .position("Developer")
                .email("john.doe@example.com")
                .salary(50000.0)
                .createdAt(ZonedDateTime.now())
                .modifiedAt(ZonedDateTime.now())
                .build();
    }

    @Test
    void testAddEmployee() {
        database.add(employee);
        assertTrue(database.get(employee.getEmployeeId()).isPresent());
        assertEquals(employee, database.get(employee.getEmployeeId()).get());
    }

    @Test
    void testAddEmployeeDuplicate() {
        database.add(employee);
        assertThrows(EntityAlreadyExistsException.class, () -> database.add(employee));
    }

    @Test
    void testUpdateEmployee() {
        database.add(employee);
        Employee updatedEmployee = Employee.builder()
                .employeeId(employee.getEmployeeId())
                .name("Jane Doe")
                .position("Lead Developer")
                .email("jane.doe@example.com")
                .salary(75000.0)
                .createdAt(employee.getCreatedAt())
                .modifiedAt(ZonedDateTime.now())
                .build();

        database.update(employee.getEmployeeId(), updatedEmployee);
        assertEquals(updatedEmployee, database.get(employee.getEmployeeId()).get());
    }

    @Test
    void testUpdateNonExistentEmployee() {
        assertThrows(EntityNotFoundException.class, () -> database.update(UUID.randomUUID(), employee));
    }

    @Test
    void testGetNonExistentEmployee() {
        assertTrue(database.get(UUID.randomUUID()).isEmpty());
    }

    @Test
    void testDeleteEmployee() {
        database.add(employee);
        database.delete(employee.getEmployeeId());
        assertTrue(database.get(employee.getEmployeeId()).isEmpty());
    }

    @Test
    void testDeleteNonExistentEmployee() {
        assertThrows(EntityNotFoundException.class, () -> database.delete(UUID.randomUUID()));
    }

    @Test
    void testGetAllEmployees() {
        database.add(employee);
        Employee employee2 = Employee.builder()
                .employeeId(UUID.randomUUID())
                .name("Alice Smith")
                .position("Manager")
                .email("alice.smith@example.com")
                .salary(80000.0)
                .createdAt(ZonedDateTime.now())
                .modifiedAt(ZonedDateTime.now())
                .build();
        database.add(employee2);

        assertEquals(2, database.getAll().size());
    }

    @Test
    void testConcurrentAccess() throws InterruptedException {
        try (ExecutorService executor = Executors.newFixedThreadPool(10)) {
            for (int i = 0; i < 1000; i++) {
                final int index = i;
                executor.submit(() -> {
                    Employee emp = Employee.builder()
                            .employeeId(UUID.randomUUID())
                            .name("Employee " + index)
                            .position("Position " + index)
                            .email("employee" + index + "@example.com")
                            .salary(50000.0 + index)
                            .createdAt(ZonedDateTime.now())
                            .modifiedAt(ZonedDateTime.now())
                            .build();
                    database.add(emp);
                });
            }

            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.MINUTES);
        }

        assertEquals(1000, database.getAll().size());
    }
}
