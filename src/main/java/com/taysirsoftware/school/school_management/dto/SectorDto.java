package com.taysirsoftware.school.school_management.dto;

public class SectorDto {
    private Long idSector;
    private String nameSector;
    private String acronym;
    private String description;

    public SectorDto() {
    }

    public SectorDto(String nameSector, String acronym, String description) {
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

}
