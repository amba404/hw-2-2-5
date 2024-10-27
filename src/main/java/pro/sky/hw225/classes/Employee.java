package pro.sky.hw225.classes;

import pro.sky.hw225.exceptions.EmployeeIllegalArgumentsException;

import java.util.Objects;

public class Employee {
    final private String firstName;
    final private String lastName;

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
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
