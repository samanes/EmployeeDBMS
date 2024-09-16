package model;

import org.junit.jupiter.api.Test;
import java.time.ZonedDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link Employee} class.
 */
class EmployeeTest {

    @Test
    void testEmployeeCreation() {
        UUID employeeId = UUID.randomUUID();
        ZonedDateTime now = ZonedDateTime.now();
        Employee employee = Employee.builder()
                .employeeId(employeeId)
                .name("John Doe")
                .position("Developer")
                .email("john.doe@example.com")
                .salary(50000.0)
                .createdAt(now)
                .modifiedAt(now)
                .build();

        assertEquals(employeeId, employee.getEmployeeId());
        assertEquals("John Doe", employee.getName());
        assertEquals("Developer", employee.getPosition());
        assertEquals("john.doe@example.com", employee.getEmail());
        assertEquals(50000.0, employee.getSalary());
        assertEquals(now, employee.getCreatedAt());
        assertEquals(now, employee.getModifiedAt());
    }
}
