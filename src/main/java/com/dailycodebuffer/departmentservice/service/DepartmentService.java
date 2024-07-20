package com.dailycodebuffer.departmentservice.service;

import com.dailycodebuffer.departmentservice.model.Department;
import com.dailycodebuffer.departmentservice.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DepartmentService {
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(long departmentId) {
        return departmentRepository.findById(departmentId).orElseThrow(RuntimeException::new);
    }

    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }
}
