package pro.sky.hw225.interfaces;

import pro.sky.hw225.classes.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {
    Collection<Employee> getList(int departmentId);

    Map<Integer, List<Employee>> getAll();

    Double getEmployeeSalaryMax(int departmentId);

    Double getEmployeeSalaryMin(int departmentId);

    Double getEmployeeSalarySum(int departmentId);
}
