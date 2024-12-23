package pro.sky.hw225.classes;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.hw225.exceptions.EmployeeNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    private DepartmentServiceImpl departmentService;

    private static List<Employee> employees;

    @Mock
    private EmployeeServiceImpl employeeService;

    @BeforeAll
    static void beforeAll() {
        Employee employee1 = new Employee("A", "A");
        Employee employee2 = new Employee("B", "B");
        Employee employee3 = new Employee("C", "C");
        employee1.setDepartment(1);
        employee2.setDepartment(1);
        employee3.setDepartment(2);
        employee1.setSalary(1.01);
        employee2.setSalary(2.02);
        employee3.setSalary(3.03);
        employees = List.of(employee1, employee2, employee3);
    }


    @BeforeEach
    void setUp() {
        departmentService = new DepartmentServiceImpl(employeeService);

        Mockito.when(employeeService.getList()).thenReturn(employees);
    }

    @Test
    void testGetListDept1() {
        Collection<Employee> list = departmentService.getList(1);

        Assertions.assertNotNull(list);
        Assertions.assertEquals(2, list.size());
    }

    @Test
    void testGetListDept11() {
        Collection<Employee> list = departmentService.getList(11);

        Assertions.assertNotNull(list);
        Assertions.assertEquals(0, list.size());
    }

    @Test
    void getEmployeeSalaryMax() {
        Assertions.assertEquals(2.02, departmentService.getEmployeeSalaryMax(1), 0.001);
    }

    @Test
    void getEmployeeSalaryMin() {
        Assertions.assertEquals(1.01, departmentService.getEmployeeSalaryMin(1), 0.001);
    }

    @Test
    void getEmployeeSalarySum() {
        Assertions.assertEquals(3.03, departmentService.getEmployeeSalarySum(1), 0.001);
    }

    @Test
    void getEmployeeSalaryMinThrowsException() {
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> departmentService.getEmployeeSalaryMin(11));
    }

    @Test
    void getEmployeeSalaryMaxThrowsException() {
        Assertions.assertThrows(EmployeeNotFoundException.class, () -> departmentService.getEmployeeSalaryMax(11));
    }

    @Test
    void getEmployeeSalarySumWrongDept() {
        Assertions.assertEquals(0.0, departmentService.getEmployeeSalarySum(11));
    }

    @Test
    void getAll() {
        Map<Integer, List<Employee>> all = departmentService.getAll();

        Assertions.assertNotNull(all);
        Assertions.assertEquals(2, all.size());

        List<Employee> dept1 = all.get(1);

        Assertions.assertNotNull(dept1);
        Assertions.assertEquals(2, dept1.size());
    }
}