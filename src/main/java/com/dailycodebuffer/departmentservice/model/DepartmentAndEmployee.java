package com.dailycodebuffer.departmentservice.model;


import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentAndEmployee {
    private Long id;
    private String name;
    List<Employee> employees;
}
