package com.pfe.timetrack.repository;

import com.pfe.timetrack.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);

}
