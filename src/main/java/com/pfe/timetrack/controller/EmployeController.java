package com.pfe.timetrack.controller;

import com.pfe.timetrack.models.Employe;
import com.pfe.timetrack.repository.IEmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employes")
public class EmployeController {

    private final IEmployeRepository employeRepository;

    @Autowired
    public EmployeController(IEmployeRepository employeService) {
        this.employeRepository = employeService;
    }

    // Get all employes
    @GetMapping
    public ResponseEntity<List<Employe>> getAllemployes() {
        List<Employe> employes = employeRepository.findAll();
        return new ResponseEntity<>(employes, HttpStatus.OK);
    }

    // Get employe by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employe> getEmployeById(@PathVariable Long id) {
        return new ResponseEntity<>(employeRepository.findById(id).get(), HttpStatus.OK);
    }

    // Create new employe
    @PostMapping
    public ResponseEntity<Employe> createemploye(@RequestBody Employe employe) {
        Employe savedemploye = employeRepository.save(employe);
        return new ResponseEntity<>(savedemploye, HttpStatus.CREATED);
    }

    // Update employe
    @PutMapping("/{id}")
    public ResponseEntity<Employe> updateemploye(@PathVariable Long id, @RequestBody Employe employeDetails) {
        Optional<Employe> employeOptional = employeRepository.findById(id);
        if (employeOptional.isPresent()) {
            return new ResponseEntity<>(employeRepository.save(employeDetails), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    // Delete employe
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteemploye(@PathVariable Long id) {
        employeRepository.delete(employeRepository.findById(id).get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
