package pro.sky.hw225.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.hw225.classes.Employee;
import pro.sky.hw225.exceptions.EmployeeException;
import pro.sky.hw225.interfaces.DepartmentServiceInterface;

import java.util.Collection;

@RestController
@RequestMapping("/departments")
public class DepartmentsController {
    final private DepartmentServiceInterface departmentService;

    public DepartmentsController(DepartmentServiceInterface departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/create-test")
    public Collection<Employee> getCreateTest() {
        return departmentService.getCreateTest();
    }

    @GetMapping("/max-salary")
    public Employee departmentMaxSalary(@RequestParam(defaultValue = "0") int departmentId) {
        return departmentService.getEmployeeMaxSalary(departmentId);
    }

    @GetMapping("/min-salary")
    public Employee departmentMinSalary(@RequestParam(defaultValue = "0") int departmentId) {
        return departmentService.getEmployeeMinSalary(departmentId);
    }

    @RequestMapping(value = {"/all"}, method = RequestMethod.GET)
    public Collection<Employee> getAll(@RequestParam(defaultValue = "0") int departmentId) {
        return departmentService.getList(departmentId);
    }

    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<String> handleException(EmployeeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
