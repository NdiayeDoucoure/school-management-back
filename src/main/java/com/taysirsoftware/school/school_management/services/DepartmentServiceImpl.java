package com.taysirsoftware.school.school_management.services;

import com.taysirsoftware.school.school_management.dto.DepartmentDto;
import com.taysirsoftware.school.school_management.dto.SectorDto;
import com.taysirsoftware.school.school_management.entities.Department;
import com.taysirsoftware.school.school_management.entities.Sector;
import com.taysirsoftware.school.school_management.exception.ResourceNotFoundException;
import com.taysirsoftware.school.school_management.repo.DepartmentRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<DepartmentDto>> getAllDepartments() {
        List<DepartmentDto> departments = departmentRepo.findAll().stream()
                .map(dept -> {
                    DepartmentDto dto = new DepartmentDto();
                    dto.setIdDepartment(dept.getIdDepartment());
                    dto.setNameDepartment(dept.getNameDepartment());
                    dto.setDescriptionDepartment(dept.getDescriptionDepartment());
                    return dto;
                })
                .collect(Collectors.toList());
        return new ResponseEntity<>(departments, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DepartmentDto> getDepartmentById(Long id) {
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
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DepartmentDto> createDepartment(DepartmentDto departmentDto) {
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

        DepartmentDto departmentResponseDto = new DepartmentDto(
                savedDepartment.getIdDepartment(),
                savedDepartment.getNameDepartment(),
                savedDepartment.getDescriptionDepartment(),
                savedDepartment.getSectors().stream().map(sector -> new SectorDto(sector.getNameSector(), sector.getAcronym(), sector.getDescription())).collect(Collectors.toList())
        );

        return new ResponseEntity<>(departmentResponseDto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DepartmentDto> updateDepartment(Long id, DepartmentDto departmentDto) {
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

        DepartmentDto departmentResponseDto = new DepartmentDto(
                updatedDepartment.getIdDepartment(),
                updatedDepartment.getNameDepartment(),
                updatedDepartment.getDescriptionDepartment(),
                updatedDepartment.getSectors().stream().map(sector -> new SectorDto(sector.getNameSector(), sector.getAcronym(), sector.getDescription())).collect(Collectors.toList())
        );

        return new ResponseEntity<>(departmentResponseDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> deleteDepartment(Long id) {
        Department department = departmentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + id));
        departmentRepo.delete(department);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
