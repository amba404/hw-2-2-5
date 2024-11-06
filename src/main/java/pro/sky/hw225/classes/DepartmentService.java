package pro.sky.hw225.classes;

import org.springframework.stereotype.Service;
import pro.sky.hw225.interfaces.DepartmentServiceInterface;

import java.util.Collection;
import java.util.Comparator;
import java.util.Random;

@Service
public class DepartmentService implements DepartmentServiceInterface {
    private final Random r = new Random();
    final private EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Collection<Employee> getList(int departmentId) {
        return employeeService.getList().stream()
                .filter(e -> {
                    return departmentId < 1 || e.getDepartment() == departmentId;
                })
                .sorted(Comparator
                        .comparingInt(Employee::getDepartment)
                        .thenComparing(Employee::getFullName))
                .toList();
    }

    @Override
    public Collection<Employee> getCreateTest() {

        String[] firstNames = {"Иван", "Петр", "Семен", "Сидор", "Алексей", "Александр", "Олег", "Эдуард", "Онотоле"};
        String[] lastNames = {"Иванов", "Петров", "Семенов", "Сидоров", "Алексеев", "Тринадцатый"};

        for (int i = 0; i < 100; i++) {
            String firstName = firstNames[r.nextInt(firstNames.length)];
            String lastName = lastNames[r.nextInt(lastNames.length)];

            Employee employee = employeeService.add(firstName, lastName);
            employee.setDepartment(r.nextInt(1, 6));
            employee.setSalary(r.nextDouble(50_000, 150_000));
        }
        return getList(0);
    }

    @Override
    public Employee getEmployeeMaxSalary(int departmentId) {
        Employee result = null;
        return result;
    }
}
