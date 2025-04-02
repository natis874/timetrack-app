package com.pfe.timetrack.controllers;

import com.pfe.timetrack.dtos.EmployeDto;
import com.pfe.timetrack.mappers.IEmployeMapper;
import com.pfe.timetrack.models.Employe;
import com.pfe.timetrack.repositories.IEmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/employes")
public class EmployeController {

    private final IEmployeRepository employeRepository;
    private final IEmployeMapper employeMapper;

    @Autowired
    public EmployeController(IEmployeRepository employeRepository,
                             IEmployeMapper employeMapper) {
        this.employeRepository = employeRepository;
        this.employeMapper = employeMapper;
    }

    // Get all employes
    @GetMapping
    public ResponseEntity<List<EmployeDto>> getAllEmployes() {
        List<EmployeDto> employes = employeRepository.findAll()
                .stream()
                .map(employeMapper::toDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(employes, HttpStatus.OK);
    }

    // Get employe by ID
    @GetMapping("/{id}")
    public ResponseEntity<EmployeDto> getEmployeById(@PathVariable Long id) {
        Optional<Employe> employe = employeRepository.findById(id);
        return employe.map(e -> new ResponseEntity<>(employeMapper.toDto(e), HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Create new employe
    @PostMapping
    public ResponseEntity<EmployeDto> createEmploye(@RequestBody EmployeDto employeDto) {
        Employe employe = employeMapper.toEntity(employeDto);
        Employe savedEmploye = employeRepository.save(employe);
        return new ResponseEntity<>(employeMapper.toDto(savedEmploye), HttpStatus.CREATED);
    }

    // Update employe
    @PutMapping("/{id}")
    public ResponseEntity<EmployeDto> updateEmploye(@PathVariable Long id,
                                                    @RequestBody EmployeDto employeDto) {
        return employeRepository.findById(id)
                .map(existingEmploye -> {
                    // Mise à jour des champs
                    existingEmploye.setNom(employeDto.getNom());
                    existingEmploye.setPrenom(employeDto.getPrenom());
                    existingEmploye.setEmail(employeDto.getEmail());
                    existingEmploye.setPoste(employeDto.getPoste());
                    existingEmploye.setDateEmbauche(employeDto.getDateEmbauche());
                    existingEmploye.setTypeContrat(employeDto.getTypeContrat());
                    existingEmploye.setStatut(employeDto.getStatut());
                    existingEmploye.setDateFinContrat(employeDto.getDateFinContrat());

                    // Note: Le manager doit être géré séparément via un service
                    // existingEmploye.setMananger(...);

                    Employe updatedEmploye = employeRepository.save(existingEmploye);
                    return new ResponseEntity<>(employeMapper.toDto(updatedEmploye), HttpStatus.OK);
                })
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Delete employe
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmploye(@PathVariable Long id) {
        if (employeRepository.existsById(id)) {
            employeRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
