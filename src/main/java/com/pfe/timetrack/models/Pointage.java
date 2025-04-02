package com.pfe.timetrack.models;

import com.pfe.timetrack.enums.TypePointage;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "pointages")
public class Pointage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    private LocalTime heureArrivee;

    @Column
    private LocalTime heureDepart;

    @Enumerated(EnumType.STRING)
    private TypePointage type;

    @ManyToOne
    @JoinColumn(name = "employe_id", nullable = false)
    private Employe employe;
}
