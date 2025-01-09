package com.taysirsoftware.school.school_management.repo;

import com.taysirsoftware.school.school_management.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepo extends JpaRepository<Department, Long> {
}
