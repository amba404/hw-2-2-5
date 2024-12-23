package pro.sky.hw225.classes;

import org.springframework.stereotype.Service;
import pro.sky.hw225.exceptions.EmployeeNotFoundException;
import pro.sky.hw225.interfaces.DepartmentService;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final Random r = new Random();
    final private EmployeeServiceImpl employeeService;

    public DepartmentServiceImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    private Stream<Employee> getListByDepartment(int departmentId) {
        return employeeService.getList().stream()
                .filter(e -> e.getDepartment() == departmentId);
    }

    @Override
    public Collection<Employee> getList(int departmentId) {
        return getListByDepartment(departmentId)
                .sorted(Comparator.comparing(Employee::getFullName))
                .toList();
    }

    @Override
    public Double getEmployeeSalaryMax(int departmentId) {
        OptionalDouble result = getListByDepartment(departmentId)
                .mapToDouble(Employee::getSalary)
                .max();
        return result.orElseThrow(() -> new EmployeeNotFoundException("Что-то пошло не так..."));
    }

    @Override
    public Double getEmployeeSalaryMin(int departmentId) {
        OptionalDouble result = getListByDepartment(departmentId)
                .mapToDouble(Employee::getSalary)
                .min();
        return result.orElseThrow(() -> new EmployeeNotFoundException("Что-то пошло не так..."));
    }

    @Override
    public Double getEmployeeSalarySum(int departmentId) {
        return getListByDepartment(departmentId)
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    @Override
    public Map<Integer, List<Employee>> getAll() {
        return employeeService.getList().stream()
                .sorted(Comparator.comparing(Employee::getFullName))
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

}
