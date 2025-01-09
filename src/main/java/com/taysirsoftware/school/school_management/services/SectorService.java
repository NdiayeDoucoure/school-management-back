package com.taysirsoftware.school.school_management.services;

import com.taysirsoftware.school.school_management.dto.SectorDto;

import java.util.List;

public interface SectorService {
    SectorDto addSector(Long departmentId, SectorDto sectorDTO);
}
