package com.taysirsoftware.school.school_management.entities;
import jakarta.persistence.*;
import java.util.List;
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDepartment;

    @Column(nullable = false)
    private String nameDepartment;

    private String descriptionDepartment;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sector> sectors;

    public Long getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(Long idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public String getDescriptionDepartment() {
        return descriptionDepartment;
    }

    public void setDescriptionDepartment(String descriptionDepartment) {
        this.descriptionDepartment = descriptionDepartment;
    }

    public List<Sector> getSectors() {
        return sectors;
    }

    public void setSectors(List<Sector> sectors) {
        this.sectors = sectors;
    }
}
