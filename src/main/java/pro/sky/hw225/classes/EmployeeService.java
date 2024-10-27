package pro.sky.hw225.classes;

import org.springframework.stereotype.Service;
import pro.sky.hw225.exceptions.EmployeeAlreadyAddedException;
import pro.sky.hw225.exceptions.EmployeeNotFoundException;
import pro.sky.hw225.exceptions.EmployeeStorageIsFullException;
import pro.sky.hw225.interfaces.EmployeeServiceInterface;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService implements EmployeeServiceInterface {
    final private int maxEmployeeCount = 10;
    private List<Employee> employees = new ArrayList<>();

    @Override
    public String add(String firstName, String lastName) {
        if (employees.size() >= maxEmployeeCount) {
            throw new EmployeeStorageIsFullException();
        }

        Employee newEmployee = new Employee(firstName, lastName);

        if (!employees.contains(newEmployee)) {
            employees.add(newEmployee);
            return "Added: " + newEmployee;
        } else {
            throw new EmployeeAlreadyAddedException();
        }
    }

    @Override
    public String remove(String firstName, String lastName) {
        Employee delEmployee = new Employee(firstName, lastName);
        if (employees.contains(delEmployee)) {
            employees.remove(delEmployee);
            return "Removed: " + delEmployee;
        } else {
            throw new EmployeeNotFoundException();
        }
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee findEmployee = new Employee(firstName, lastName);
        Employee result = find(findEmployee);
        if (result != null) {
            return result;
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Object getList() {
        return List.of(employees);
    }

    private Employee find(Employee findEmployee) {
        int idx = employees.indexOf(findEmployee);
        return idx < 0 ? null : employees.get(idx);
    }
}
