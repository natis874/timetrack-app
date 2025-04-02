package com.pfe.timetrack.dtos;

import com.pfe.timetrack.enums.StatutCompte;
import com.pfe.timetrack.enums.TypeContrat;
import lombok.Data;

import java.util.Date;

@Data
public class EmployeDto {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String poste;
    private String password;
    private Long managerId; // Juste l'ID au lieu de l'objet complet
    private Date dateEmbauche;
    private TypeContrat typeContrat;
    private StatutCompte statut;
    private Date dateFinContrat;
}
