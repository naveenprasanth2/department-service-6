package com.dailycodebuffer.departmentservice.repository;

import com.dailycodebuffer.departmentservice.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findByName(String departmentName);
}
