package pro.sky.hw225.classes;

import org.springframework.stereotype.Service;
import pro.sky.hw225.exceptions.EmployeeNotFoundException;
import pro.sky.hw225.interfaces.DepartmentServiceInterface;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import java.util.Random;

@Service
public class DepartmentService implements DepartmentServiceInterface {
    private final Random r = new Random();
    final private EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * @param departmentId {0|N}
     * @return List of employees:
     * <p>  if departmentId == 0  - List of ALL employees
     * <p>  else - List of employees by departmentId (maybe empty)
     */
    private Collection<Employee> getListByDepartment(int departmentId) {
        if (departmentId == 0) {
            return getListAll();
        } else {
            return employeeService.getList().stream()
                    .filter(e.getDepartment() == departmentId)
                    .toList();
        }
    }

    private Collection<Employee> getListAll() {
        return employeeService.getList().stream().toList();
    }

    @Override
    public Collection<Employee> getList(int departmentId) {
        return getListByDepartment(departmentId).stream()
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
        Optional<Employee> result = getListByDepartment(departmentId).stream()
                .max(Comparator.comparingDouble(Employee::getSalary));
        return result.orElseThrow(() -> new EmployeeNotFoundException("Что-то пошло не так..."));
    }

    @Override
    public Employee getEmployeeMinSalary(int departmentId) {
        Optional<Employee> result = getListByDepartment(departmentId).stream()
                .min(Comparator.comparingDouble(Employee::getSalary));
        return result.orElseThrow(() -> new EmployeeNotFoundException("Что-то пошло не так..."));
    }
}
