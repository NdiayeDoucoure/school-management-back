package com.taysirsoftware.school.school_management.services;

import com.taysirsoftware.school.school_management.dto.DepartmentDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DepartmentService {
    ResponseEntity<List<DepartmentDto>> getAllDepartments();
    ResponseEntity<DepartmentDto> getDepartmentById(Long id);
    ResponseEntity<DepartmentDto> createDepartment(DepartmentDto departmentDto);
    ResponseEntity<DepartmentDto> updateDepartment(Long id, DepartmentDto departmentDto);
    ResponseEntity<Void> deleteDepartment(Long id);
}
