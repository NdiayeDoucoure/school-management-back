package com.taysirsoftware.school.school_management.services;

import com.taysirsoftware.school.school_management.dto.DepartmentDto;
import com.taysirsoftware.school.school_management.dto.SectorDto;
import com.taysirsoftware.school.school_management.entities.Department;
import com.taysirsoftware.school.school_management.entities.Sector;
import com.taysirsoftware.school.school_management.exception.ResourceNotFoundException;
import com.taysirsoftware.school.school_management.repo.DepartmentRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepo departmentRepo;

    public DepartmentServiceImpl(DepartmentRepo departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    @Override
    public List<DepartmentDto> getAllDepartments() {
        return departmentRepo.findAll().stream()
                .map(dept -> {
                    DepartmentDto dto = new DepartmentDto();
                    dto.setIdDepartment(dept.getIdDepartment());
                    dto.setNameDepartment(dept.getNameDepartment());
                    dto.setDescriptionDepartment(dept.getDescriptionDepartment());
                    return dto; // Pas de filières incluses ici
                })
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto getDepartmentById(Long id) {
        Department department = departmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + id));
        DepartmentDto dto = new DepartmentDto();
        dto.setIdDepartment(department.getIdDepartment());
        dto.setNameDepartment(department.getNameDepartment());
        dto.setDescriptionDepartment(department.getDescriptionDepartment());
        dto.setSectors(department.getSectors().stream().map(sector -> {
            SectorDto sectorDto = new SectorDto();
            sectorDto.setIdSector(sector.getIdSector());
            sectorDto.setNameSector(sector.getNameSector());
            sectorDto.setAcronym(sector.getAcronym());
            sectorDto.setDescription(sector.getDescription());
            return sectorDto;
        }).collect(Collectors.toList()));
        return dto;
    }

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        Department department = new Department();
        department.setNameDepartment(departmentDto.getNameDepartment());
        department.setDescriptionDepartment(departmentDto.getDescriptionDepartment());

        if (departmentDto.getSectors() != null) {
            department.setSectors(departmentDto.getSectors().stream().map(sectorDto -> {
                Sector sector = new Sector();
                sector.setNameSector(sectorDto.getNameSector());
                sector.setAcronym(sectorDto.getAcronym());
                sector.setDescription(sectorDto.getDescription());
                sector.setDepartment(department);
                return sector;
            }).collect(Collectors.toList()));
        }

        Department savedDepartment = departmentRepo.save(department);
        return getDepartmentById(savedDepartment.getIdDepartment());
    }

    @Override
    public DepartmentDto updateDepartment(Long id, DepartmentDto departmentDto) {
        Department department = departmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + id));

        department.setNameDepartment(departmentDto.getNameDepartment());
        department.setDescriptionDepartment(departmentDto.getDescriptionDepartment());

        if (departmentDto.getSectors() != null) {
            department.getSectors().clear();
            department.getSectors().addAll(departmentDto.getSectors().stream().map(sectorDto -> {
                Sector sector = new Sector();
                sector.setNameSector(sectorDto.getNameSector());
                sector.setAcronym(sectorDto.getAcronym());
                sector.setDescription(sectorDto.getDescription());
                sector.setDepartment(department);
                return sector;
            }).collect(Collectors.toList()));
        }

        Department updatedDepartment = departmentRepo.save(department);
        return getDepartmentById(updatedDepartment.getIdDepartment());
    }

    @Override
    public void deleteDepartment(Long id) {
        Department department = departmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + id));
        departmentRepo.delete(department);
    }
}

