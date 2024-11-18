package pro.sky.hw225.interfaces;

import pro.sky.hw225.classes.Employee;

import java.util.Collection;

public interface DepartmentServiceInterface {
    Collection<Employee> getList(int departmentId);

    Collection<Employee> getCreateTest();

    Employee getEmployeeMaxSalary(int departmentId);

    Employee getEmployeeMinSalary(int departmentId);
}
