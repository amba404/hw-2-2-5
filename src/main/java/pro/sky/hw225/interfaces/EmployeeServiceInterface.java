package pro.sky.hw225.interfaces;

import pro.sky.hw225.classes.Employee;

public interface EmployeeServiceInterface {
    String add(String firstName, String lastName);

    String remove(String firstName, String lastName);

    Employee find(String firstName, String lastName);

    Object getList();
}
