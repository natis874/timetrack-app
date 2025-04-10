CREATE TABLE employes (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         nom VARCHAR(255) NOT NULL,
                         prenom VARCHAR(255) NOT NULL,
                         email VARCHAR(255) NOT NULL,
                         poste VARCHAR(255) NOT NULL,
                         password VARCHAR(255) NOT NULL,
                         manager_id INT,
                         date_embauche DATE NOT NULL,
                         statut VARCHAR(10) CHECK (statut IN ('actif', 'inactif')) NOT NULL,
                         type_contrat VARCHAR(3) CHECK (type_contrat IN ('CDI', 'CDD')) NOT NULL,
                         date_fin_contrat DATE,
                         FOREIGN KEY (manager_id) REFERENCES employes(id)
);

CREATE TABLE pointages (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           date DATE NOT NULL,
                           heure_arrivee TIME NULL,
                           heure_depart TIME NULL,
                           type VARCHAR(20) NOT NULL CHECK (type IN ('PRESENT', 'TELETRAVAIL', 'CONGE')),
                           employe_id BIGINT NOT NULL,
                           CONSTRAINT fk_employe FOREIGN KEY (employe_id) REFERENCES employes(id) ON DELETE CASCADE
);



