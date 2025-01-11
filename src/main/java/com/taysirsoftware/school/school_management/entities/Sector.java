package com.taysirsoftware.school.school_management.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
}
