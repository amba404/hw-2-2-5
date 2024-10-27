package pro.sky.hw225.interfaces;

import pro.sky.hw225.classes.Employee;

import java.util.ArrayList;
import java.util.List;

public interface EmployeeServiceInterface {
    String add(String firstName, String lastName);

    String remove(String firstName, String lastName);

    Employee find(String firstName, String lastName);

    Object getList();
}
