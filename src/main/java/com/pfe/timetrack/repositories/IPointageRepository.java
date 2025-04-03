package com.pfe.timetrack.repositories;

import com.pfe.timetrack.models.Pointage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IPointageRepository extends JpaRepository<Pointage, Long> {
    List<Pointage> findByEmployeId(Long employeId);

    boolean existsByEmployeIdAndDate(Long employeId, LocalDate date);

    List<Pointage> findByEmployeIdAndDate(Long employeId, LocalDate date);
}
