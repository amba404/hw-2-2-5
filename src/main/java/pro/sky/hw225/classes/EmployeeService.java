package pro.sky.hw225.classes;

import org.springframework.stereotype.Service;
import pro.sky.hw225.exceptions.EmployeeAlreadyAddedException;
import pro.sky.hw225.exceptions.EmployeeNotFoundException;
import pro.sky.hw225.exceptions.EmployeeStorageIsFullException;
import pro.sky.hw225.interfaces.EmployeeServiceInterface;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeService implements EmployeeServiceInterface {
    final private int maxEmployeeCount = 10;
    private final List<Employee> employees = new ArrayList<>();

    @Override
    public Employee add(String firstName, String lastName) {
        if (employees.size() >= maxEmployeeCount) {
            throw new EmployeeStorageIsFullException("Все вакансии заполнены");
        }

        Employee newEmployee = new Employee(firstName, lastName);

        if (!employees.contains(newEmployee)) {
            employees.add(newEmployee);
            return newEmployee;
        } else {
            throw new EmployeeAlreadyAddedException("Сотрудник уже добавлен: " + newEmployee);
        }
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee delEmployee = new Employee(firstName, lastName);
        if (employees.contains(delEmployee)) {
            employees.remove(delEmployee);
            return delEmployee;
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден: " + delEmployee);
        }
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee findEmployee = new Employee(firstName, lastName);
        Employee result = find(findEmployee);
        if (result != null) {
            return result;
        }
        throw new EmployeeNotFoundException("Сотрудник не найден: " + findEmployee);
    }

    @Override
    public Collection<Employee> getList() {
        return Collections.unmodifiableList(employees);
    }

    private Employee find(Employee findEmployee) {
        int idx = employees.indexOf(findEmployee);
        return idx < 0 ? null : employees.get(idx);
    }
}
