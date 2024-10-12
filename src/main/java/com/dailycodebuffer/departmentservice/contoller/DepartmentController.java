package com.dailycodebuffer.departmentservice.contoller;


import com.dailycodebuffer.departmentservice.client.EmployeeClient;
import com.dailycodebuffer.departmentservice.model.Department;
import com.dailycodebuffer.departmentservice.model.DepartmentAndEmployee;
import com.dailycodebuffer.departmentservice.model.Employee;
import com.dailycodebuffer.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
@AllArgsConstructor
@Log4j2
public class DepartmentController {
    private DepartmentService departmentService;
    private EmployeeClient employeeClient;

    @PostMapping
    @CachePut(value = "departments", key = "#result.body.id")
    public ResponseEntity<Department> createDepartment(@RequestBody Department department) {
        Department department1 = departmentService.addDepartment(department);
        return new ResponseEntity<>(department1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentAndEmployee>> getAllDepartments() {
        return new ResponseEntity<>(departmentService.getAllDepartments(), HttpStatus.OK);
    }

    @GetMapping("/with-employees")
    @Cacheable(value = "departments", key = "#root.method.name")
    public ResponseEntity<List<DepartmentAndEmployee>> getDepartmentsWithDepartment() {
        return new ResponseEntity<>(departmentService.getAllDepartments()
                .stream()
                .map(department -> {
                    List<Employee> employees = employeeClient.getEmployeeByDepartmentId(department.getId()).getBody();
                    return new DepartmentAndEmployee(department.getId(), department .getName(), employees);
                })
                .toList(), HttpStatus.OK);
    }
}