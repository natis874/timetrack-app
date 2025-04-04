package com.pfe.timetrack.models;

import com.pfe.timetrack.enums.StatutCompte;
import com.pfe.timetrack.enums.TypeContrat;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "employes")
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;

    private String prenom;

    private String email;

    private String poste;

    private String password;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private Employe manager;

    @Temporal(TemporalType.DATE)
    private Date dateEmbauche;

    @Enumerated(EnumType.STRING)
    private TypeContrat typeContrat;

    @Enumerated(EnumType.STRING)
    private StatutCompte statut;

    @Temporal(TemporalType.DATE)
    private Date dateFinContrat;

    @OneToMany(mappedBy = "employe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pointage> pointages;
}
