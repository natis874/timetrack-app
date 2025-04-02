package com.pfe.timetrack.dtos;

import com.pfe.timetrack.enums.TypePointage;

import java.time.LocalDate;
import java.time.LocalTime;

public class PointageDto {
    private Long id;
    private LocalDate date;
    private LocalTime heureArrivee;
    private LocalTime heureDepart;
    private TypePointage type;
    private Long employeId; // On utilise seulement l'ID de l'employé dans le DTO

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(LocalTime heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public LocalTime getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(LocalTime heureDepart) {
        this.heureDepart = heureDepart;
    }

    public TypePointage getType() {
        return type;
    }

    public void setType(TypePointage type) {
        this.type = type;
    }

    public Long getEmployeId() {
        return employeId;
    }

    public void setEmployeId(Long employeId) {
        this.employeId = employeId;
    }
}
