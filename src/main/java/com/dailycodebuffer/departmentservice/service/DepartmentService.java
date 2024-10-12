package com.dailycodebuffer.departmentservice.service;

import com.dailycodebuffer.departmentservice.model.Department;
import com.dailycodebuffer.departmentservice.model.DepartmentAndEmployee;
import com.dailycodebuffer.departmentservice.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class DepartmentService {
    private DepartmentRepository departmentRepository;

    public List<DepartmentAndEmployee> getAllDepartments() {
        log.info("It internally hits database");
        return departmentRepository.findAll()
                .stream().map(x -> DepartmentAndEmployee
                        .builder()
                        .id(x.getId())
                        .name(x.getName())
                        .build()).toList();
    }

    public Department getDepartmentById(long departmentId) {
        return departmentRepository.findById(departmentId).orElseThrow(RuntimeException::new);
    }

    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }
}
