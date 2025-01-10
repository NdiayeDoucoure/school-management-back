package com.taysirsoftware.school.school_management.dto;

import java.util.List;

public class DepartmentDto {
    private Long idDepartment;
    private String nameDepartment;
    private String descriptionDepartment;
    private List<SectorDto> sectors;

    public DepartmentDto() {
    }

    public DepartmentDto(Long idDepartment, String nameDepartment, String descriptionDepartment, List<SectorDto> collect) {
    }

    // Getters et setters pour idDepartment
    public Long getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Long idDepartment) {
        this.idDepartment = idDepartment;
    }

    // Getters et setters pour nameDepartment
    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    // Getters et setters pour descriptionDepartment
    public String getDescriptionDepartment() {
        return descriptionDepartment;
    }

    public void setDescriptionDepartment(String descriptionDepartment) {
        this.descriptionDepartment = descriptionDepartment;
    }

    // Getters et setters pour sectors
    public List<SectorDto> getSectors() {
        return sectors;
    }

    public void setSectors(List<SectorDto> sectors) {
        this.sectors = sectors;
    }
}
