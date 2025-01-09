package com.taysirsoftware.school.school_management.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "sectors")
public class Sector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSector;
    private String nameSector;
    private String acronym;
    private String description;
    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    public Sector() {
    }

    public Long getIdSector() {
        return idSector;
    }

    public void setIdSector(Long idSector) {
        this.idSector = idSector;
    }

    public String getNameSector() {
        return nameSector;
    }

    public void setNameSector(String nameSector) {
        this.nameSector = nameSector;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }


    @Override
    public String toString() {
        return "sector{" +
                "idSector=" + idSector +
                ", nameSector='" + nameSector + '\'' +
                ", acronym='" + acronym + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
