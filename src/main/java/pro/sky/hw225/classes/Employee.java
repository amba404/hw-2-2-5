package pro.sky.hw225.classes;

import org.jetbrains.annotations.NotNull;
import pro.sky.hw225.exceptions.EmployeeIllegalArgumentsException;

import java.util.Objects;

public class Employee {
    final private String firstName;
    final private String lastName;
    final private int minDepNumber = 1;
    final private int maxDepNumber = 5;
    private int department;
    private double salary;

    public Employee(String firstName, String lastName) {
        if (firstName == null || firstName.isBlank()) {
            throw new EmployeeIllegalArgumentsException("Имя (firstName) не должно быть пустым");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new EmployeeIllegalArgumentsException("Фамиля (lastName) не должна быть пустой");
        }
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @NotNull
    public String getFullName() {
        return lastName + " " + firstName;
    }

    public int getDepartment() {
        return department;
    }

    public void setDepartment(int department) {
        if (department >= minDepNumber && department <= maxDepNumber) {
            this.department = department;
        } else {
            throw new EmployeeIllegalArgumentsException("Недопустимый номер отдела");
        }
    }

    public double getSalary() {
        return this.salary;
    }

    public void setSalary(double salary) {
        if (salary < 0) {
            throw new EmployeeIllegalArgumentsException("Значение зарплаты меньше нуля");
        }
        this.salary = (double) Math.round(salary * 100) / 100;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return String.format("department=%d, fullName='%s', salary=%.2f", department, getFullName(), salary);
    }
}
