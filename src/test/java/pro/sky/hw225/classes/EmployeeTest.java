package pro.sky.hw225.classes;

import org.junit.jupiter.api.Test;
import pro.sky.hw225.exceptions.EmployeeIllegalArgumentsException;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    @Test
    public void testConstructor() {
        Employee employee = new Employee("John", "Doe");
        assertEquals("John", employee.getFirstName());
        assertEquals("Doe", employee.getLastName());
    }

    @Test
    public void testConstructorWithNullFirstName() {
        assertThrows(EmployeeIllegalArgumentsException.class, () -> new Employee(null, "Doe"));
    }

    @Test
    public void testConstructorWithNullLastName() {
        assertThrows(EmployeeIllegalArgumentsException.class, () -> new Employee("John", null));
    }

    @Test
    public void testConstructorWithBlankFirstName() {
        assertThrows(EmployeeIllegalArgumentsException.class, () -> new Employee(" ", "Doe"));
    }

    @Test
    public void testConstructorWithBlankLastName() {
        assertThrows(EmployeeIllegalArgumentsException.class, () -> new Employee("John", " "));
    }

    @Test
    public void testGetFullName() {
        Employee employee = new Employee("John", "Doe");
        assertEquals("Doe John", employee.getFullName());
    }

    @Test
    public void testSetDepartment() {
        Employee employee = new Employee("John", "Doe");
        employee.setDepartment(3);
        assertEquals(3, employee.getDepartment());
    }

    @Test
    public void testSetDepartmentWithInvalidValue() {
        Employee employee = new Employee("John", "Doe");
        assertThrows(EmployeeIllegalArgumentsException.class, () -> employee.setDepartment(6));
    }

    @Test
    public void testSetSalary() {
        Employee employee = new Employee("John", "Doe");
        employee.setSalary(123.456);
        assertEquals(123.46, employee.getSalary());
    }

    @Test
    public void testSetSalaryWithNegativeValue() {
        Employee employee = new Employee("John", "Doe");
        assertThrows(EmployeeIllegalArgumentsException.class, () -> employee.setSalary(-100));
    }

    @Test
    public void testEquals() {
        Employee employee1 = new Employee("John", "Doe");
        Employee employee2 = new Employee("John", "Doe");
        assertTrue(employee1.equals(employee2));
    }

    @Test
    public void testHashCode() {
        Employee employee1 = new Employee("John", "Doe");
        Employee employee2 = new Employee("John", "Doe");
        assertEquals(employee1.hashCode(), employee2.hashCode());
    }

    @Test
    public void testToString() {
        Employee employee = new Employee("John", "Dow");
        assertEquals("department=0, fullName='Dow John', salary=0,00", employee.toString());
    }
}