package com.pfe.timetrack.repository;

import com.pfe.timetrack.models.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEmployeRepository extends JpaRepository<Employe, Long> {

    Optional<Employe> findByEmail(String email);

}
