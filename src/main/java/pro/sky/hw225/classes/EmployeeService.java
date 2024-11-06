package pro.sky.hw225.classes;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import pro.sky.hw225.exceptions.EmployeeAlreadyAddedException;
import pro.sky.hw225.exceptions.EmployeeNotFoundException;
import pro.sky.hw225.exceptions.EmployeeStorageIsFullException;
import pro.sky.hw225.interfaces.EmployeeServiceInterface;

import java.util.*;

@Service
public class EmployeeService implements EmployeeServiceInterface {
    final private int maxEmployeeCount = 10;
    private final Map<String, Employee> employees = new HashMap<>();

    @Override
    public Employee add(String firstName, String lastName) {
        if (employees.size() >= maxEmployeeCount) {
            throw new EmployeeStorageIsFullException("Все вакансии заполнены");
        }

        EmployeeHelper empHlp = new EmployeeHelper(firstName, lastName);
        Employee newEmployee = new Employee(empHlp.firstName, empHlp.lastName);
        if (!employees.containsKey(empHlp.mapKey)) {
            employees.put(empHlp.mapKey, newEmployee);
            return newEmployee;
        } else {
            throw new EmployeeAlreadyAddedException("Сотрудник уже добавлен: " + newEmployee);
        }
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        EmployeeHelper empHlp = new EmployeeHelper(firstName, lastName);
        Employee delEmployee = new Employee(empHlp.firstName, empHlp.lastName);
        if (employees.containsKey(empHlp.mapKey)) {
            return employees.remove(empHlp.mapKey);
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден: " + delEmployee);
        }
    }

    @Override
    public Employee find(String firstName, String lastName) {
        EmployeeHelper empHlp = new EmployeeHelper(firstName, lastName);
        Employee findEmployee = new Employee(empHlp.firstName, empHlp.lastName);
        Employee result = employees.get(empHlp.mapKey);
        if (result != null) {
            return result;
        }
        throw new EmployeeNotFoundException("Сотрудник не найден: " + findEmployee);
    }

    @Override
    public Collection<Employee> getList() {
        return List.copyOf(employees.values());
    }

    private static class EmployeeHelper {
        final String firstName;
        final String lastName;
        final String mapKey;

        public EmployeeHelper(@NotNull String firstName, @NotNull String lastName) {
            this.firstName = getCleanName(firstName);
            this.lastName = getCleanName(lastName);
            this.mapKey = this.lastName + this.firstName;
        }

        private static @NotNull String getCleanName(@NotNull String name) {
            name = name.trim().toLowerCase(Locale.ROOT);
            if (!name.isEmpty()) {
                name = name.substring(0, 1).toUpperCase(Locale.ROOT) + name.substring(1);
            }
            return name;
        }

    }
}
