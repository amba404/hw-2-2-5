package pro.sky.hw225.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.hw225.classes.Employee;
import pro.sky.hw225.exceptions.EmployeeException;
import pro.sky.hw225.interfaces.EmployeeService;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    final private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee answerAdd(@RequestParam() String firstName, @RequestParam() String lastName) {
        return employeeService.add(firstName, lastName);
    }

    @GetMapping("/remove")
    public Employee answerRemove(@RequestParam() String firstName, @RequestParam() String lastName) {
        return employeeService.remove(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee answerFind(@RequestParam() String firstName, @RequestParam() String lastName) {
        return employeeService.find(firstName, lastName);
    }

    @RequestMapping(value = {"", "/", "/welcome"}, method = RequestMethod.GET)
    public Collection<Employee> answerAll() {
        return employeeService.getList();
    }

    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<String> handleException(EmployeeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
