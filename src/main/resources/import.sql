INSERT INTO employes (id, nom, prenom, email, poste, password, manager_id, date_embauche, type_contrat, statut, date_fin_contrat) VALUES (1, 'Dupont', 'Jean', 'jean.dupont@example.com', 'Développeur', 'password123', NULL, '2023-01-15', 'CDI', 'ACTIF', NULL);
INSERT INTO employes (id, nom, prenom, email, poste, password, manager_id, date_embauche, type_contrat, statut, date_fin_contrat) VALUES (2, 'Martin', 'Sophie', 'sophie.martin@example.com', 'Chef de projet', 'securepass', NULL, '2022-06-01', 'CDI', 'ACTIF', NULL);
INSERT INTO employes (id, nom, prenom, email, poste, password, manager_id, date_embauche, type_contrat, statut, date_fin_contrat) VALUES (3, 'Durand', 'Paul', 'paul.durand@example.com', 'Développeur', 'pass1234', 2, '2024-02-10', 'CDD', 'ACTIF', '2025-02-10');
INSERT INTO employes (id, nom, prenom, email, poste, password, manager_id, date_embauche, type_contrat, statut, date_fin_contrat) VALUES (4, 'Lemoine', 'Claire', 'claire.lemoine@example.com', 'Designer', 'designpass', 2, '2023-09-01', 'CDI', 'ACTIF', NULL);
INSERT INTO employes (id, nom, prenom, email, poste, password, manager_id, date_embauche, type_contrat, statut, date_fin_contrat) VALUES (5, 'Bernard', 'Luc', 'luc.bernard@example.com', 'Développeur', 'devpass', 1, '2023-07-20', 'CDD', 'INACTIF', '2024-07-20');

-- Pointages pour Jean Dupont (Employé ID 1)
INSERT INTO pointages (date, heure_arrivee, heure_depart, type, employe_id) VALUES ('2025-04-01', '08:30:00', '17:30:00', 'PRESENT', 1);
INSERT INTO pointages (date, heure_arrivee, heure_depart, type, employe_id) VALUES ('2025-04-02', '08:45:00', '17:15:00', 'PRESENT', 1);
-- INSERT INTO pointages (date, heure_arrivee, heure_depart, type, employe_id) VALUES ('2025-04-27', '09:00:00', '16:45:00', 'TELETRAVAIL', 1);

-- Pointages pour Sophie Martin (Employé ID 2)

INSERT INTO pointages (date, heure_arrivee, heure_depart, type, employe_id) VALUES ('2025-04-01', '09:00:00', '18:00:00', 'PRESENT', 2);
INSERT INTO pointages (date, heure_arrivee, heure_depart, type, employe_id) VALUES ('2025-04-02', '09:15:00', '17:45:00', 'PRESENT', 2);
-- INSERT INTO pointages (date, heure_arrivee, heure_depart, type, employe_id) VALUES ('2025-04-27', NULL, NULL, 'CONGE', 2);

-- Pointages pour Paul Durand (Employé ID 3)

INSERT INTO pointages (date, heure_arrivee, heure_depart, type, employe_id) VALUES ('2025-04-01', '08:00:00', '16:00:00', 'PRESENT', 3);
INSERT INTO pointages (date, heure_arrivee, heure_depart, type, employe_id) VALUES ('2025-04-02', '08:30:00', '17:00:00', 'PRESENT', 3);
-- INSERT INTO pointages (date, heure_arrivee, heure_depart, type, employe_id) VALUES ('2025-04-27', '09:00:00', '16:30:00', 'TELETRAVAIL', 3);

-- Pointages pour Claire Lemoine (Employé ID 4)

INSERT INTO pointages (date, heure_arrivee, heure_depart, type, employe_id) VALUES ('2025-04-01', '09:30:00', '18:30:00', 'PRESENT', 4);
INSERT INTO pointages (date, heure_arrivee, heure_depart, type, employe_id) VALUES ('2025-04-02', '10:00:00', '17:00:00', 'TELETRAVAIL', 4);
-- INSERT INTO pointages (date, heure_arrivee, heure_depart, type, employe_id) VALUES ('2025-04-27', '09:45:00', '17:15:00', 'PRESENT', 4);

-- Pointages pour Luc Bernard (Employé ID 5) (Inactif mais a des anciens pointages)

INSERT INTO pointages (date, heure_arrivee, heure_depart, type, employe_id) VALUES ('2023-12-10', '08:15:00', '16:45:00', 'PRESENT', 5);
INSERT INTO pointages (date, heure_arrivee, heure_depart, type, employe_id) VALUES ('2023-12-11', '08:30:00', '17:00:00', 'PRESENT', 5);
INSERT INTO pointages (date, heure_arrivee, heure_depart, type, employe_id) VALUES ('2023-12-12', NULL, NULL, 'CONGE', 5);
