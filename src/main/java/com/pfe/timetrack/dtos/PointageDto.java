package com.pfe.timetrack.dtos;

import com.pfe.timetrack.enums.TypePointage;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class PointageDto {
    private Long id;
    private LocalDate date;
    private LocalTime heureArrivee;
    private LocalTime heureDepart;
    private TypePointage type;
    private Long employeId; // On utilise seulement l'ID de l'employé dans le DTO
}
