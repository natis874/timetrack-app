CREATE TABLE employees (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         nom VARCHAR(255) NOT NULL,
                         prenom VARCHAR(255) NOT NULL,
                         email VARCHAR(255) NOT NULL,
                         poste VARCHAR(255) NOT NULL,
                         manager_id INT,
                         date_embauche DATE NOT NULL,
                         statut VARCHAR(10) CHECK (statut IN ('actif', 'inactif')) NOT NULL,
                         type_contrat VARCHAR(3) CHECK (type_contrat IN ('CDI', 'CDD')) NOT NULL,
                         date_fin_contrat DATETIME,
                         FOREIGN KEY (manager_id) REFERENCES employees(id)
);