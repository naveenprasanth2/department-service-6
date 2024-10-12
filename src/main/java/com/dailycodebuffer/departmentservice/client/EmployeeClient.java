package com.dailycodebuffer.departmentservice.client;


import com.dailycodebuffer.departmentservice.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;

import java.util.List;

@HttpExchange
public interface EmployeeClient {
    @GetExchange("/employees/department/{departmentId}")
    ResponseEntity<List<Employee>> getEmployeeByDepartmentId(@PathVariable("departmentId") Long departmentId);
}
