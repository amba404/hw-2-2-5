package pro.sky.hw225.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.hw225.classes.Employee;
import pro.sky.hw225.exceptions.EmployeeException;
import pro.sky.hw225.interfaces.EmployeeServiceInterface;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    final private EmployeeServiceInterface employeeService;

    public EmployeeController(EmployeeServiceInterface employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public String answerAdd(@RequestParam() String firstName, @RequestParam() String lastName) {
        return employeeService.add(firstName, lastName);
    }

    @GetMapping("/remove")
    public String answerRemove(@RequestParam() String firstName, @RequestParam() String lastName) {
        return employeeService.remove(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee answerFind(@RequestParam() String firstName, @RequestParam() String lastName) {
        return employeeService.find(firstName, lastName);
    }

    @GetMapping("/all")
    public Object answerAll() {
        return employeeService.getList();
    }

    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<String> handleException(EmployeeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
