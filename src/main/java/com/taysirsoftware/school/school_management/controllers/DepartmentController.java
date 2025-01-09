package com.taysirsoftware.school.school_management.controllers;

import com.taysirsoftware.school.school_management.dto.DepartmentDto;
import com.taysirsoftware.school.school_management.services.DepartmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<DepartmentDto> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    public DepartmentDto getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    @PostMapping
    public DepartmentDto createDepartment(@RequestBody DepartmentDto departmentDTO) {
        return departmentService.createDepartment(departmentDTO);
    }

    @PutMapping("/{id}")
    public DepartmentDto updateDepartment(@PathVariable Long id, @RequestBody DepartmentDto departmentDTO) {
        return departmentService.updateDepartment(id, departmentDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }
}
