package pro.sky.hw225.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.hw225.classes.Employee;
import pro.sky.hw225.exceptions.EmployeeException;
import pro.sky.hw225.interfaces.DepartmentService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentsController {
    final private DepartmentService departmentService;

    public DepartmentsController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/{id}/salary/sum")
    public Double departmentSalarySum(@PathVariable(name = "id") int departmentId) {
        return departmentService.getEmployeeSalarySum(departmentId);
    }

    @GetMapping("/{id}/salary/max")
    public Double departmentSalaryMax(@PathVariable(name = "id") int departmentId) {
        return departmentService.getEmployeeSalaryMax(departmentId);
    }

    @GetMapping("/{id}/salary/min")
    public Double departmentSalaryMin(@PathVariable(name = "id") int departmentId) {
        return departmentService.getEmployeeSalaryMin(departmentId);
    }

    @GetMapping("/{id}/employees")
    public Collection<Employee> getEmployeesByDepartmentId(@PathVariable(name = "id") int departmentId) {
        return departmentService.getList(departmentId);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> getAll() {
        return departmentService.getAll();
    }

    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<String> handleException(EmployeeException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
}
