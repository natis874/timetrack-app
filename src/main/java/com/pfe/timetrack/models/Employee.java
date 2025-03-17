package com.pfe.timetrack.models;

import com.pfe.timetrack.enums.Statut;
import com.pfe.timetrack.enums.TypeContrat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String prenom;

    private String email;

    private String poste;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employee mananger;

    @Temporal(TemporalType.DATE)
    private Date dateEmbauche;

    @Enumerated(EnumType.STRING)
    private TypeContrat typeContrat;

    @Enumerated(EnumType.STRING)
    private Statut statut;

    @Temporal(TemporalType.DATE)
    private Date dateFinContrat;
}
