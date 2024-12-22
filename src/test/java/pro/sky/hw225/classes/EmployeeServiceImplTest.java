package pro.sky.hw225.classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.hw225.exceptions.EmployeeAlreadyAddedException;
import pro.sky.hw225.exceptions.EmployeeIllegalArgumentsException;
import pro.sky.hw225.exceptions.EmployeeNotFoundException;
import pro.sky.hw225.exceptions.EmployeeStorageIsFullException;

import java.util.Collection;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmployeeServiceImplTest {

    private EmployeeServiceImpl employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeServiceImpl();
    }

    @Test
    void testAddEmployeeCorrectNames() {
        Employee employee = employeeService.add(" firstname", " lastNAME ");
        assertEquals("Firstname", employee.getFirstName());
        assertEquals("Lastname", employee.getLastName());
    }

    @Test
    void testAddEmployeeEmptyNamesThrowsException() {
        assertThrows(EmployeeIllegalArgumentsException.class, () -> employeeService.add(" ", "Name"));
    }

    @Test
    void testAddEmployeeIncorrectSymbolsThrowsException() {
        assertThrows(EmployeeIllegalArgumentsException.class, () -> employeeService.add("Name1", "Name2"));
    }

    String getRandomString(int length) {
        Random random = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return sb.toString();
    }

    void addEmployeesUnlimited() {
         while (true) {
            employeeService.add(getRandomString(5), getRandomString(5));
        }
    }

    @Test
    void testAddEmployeeStorageIsFull() {
        assertThrows(EmployeeStorageIsFullException.class, this::addEmployeesUnlimited);
    }

    @Test
    void testAddEmployeeAlreadyExist() {
        employeeService.add("firstname", "lastname");
        assertThrows(EmployeeAlreadyAddedException.class, () -> employeeService.add("firstname", "lastname"));
    }

    @Test
    void testRemoveEmployeeCorrect() {
        Employee employee = employeeService.add("firstname", "lastname");
        assertEquals(employee, employeeService.remove("firstname", "lastname"));
    }

    @Test
    void testRemoveEmployeeNotFound() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.remove("firstname", "lastname"));
    }

    @Test
    void testFindEmployeeCorrect() {
        Employee employee = employeeService.add("firstname", "lastname");
        assertEquals(employee, employeeService.find("firstname", "lastname"));
    }

    @Test
    void testFindEmployeeNotFound() {
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.find("firstname", "lastname"));
    }

    @Test
    void getList() {
        for (int i = 1; i < 4; i++){
            employeeService.add(getRandomString(i), getRandomString(i));
        }
        Collection<Employee> list = employeeService.getList();
        assertEquals(3, list.size());
    }
}