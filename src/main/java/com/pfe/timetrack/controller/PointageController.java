package com.pfe.timetrack.controller;

import com.pfe.timetrack.models.Pointage;
import com.pfe.timetrack.repository.IPointageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pointages")
public class PointageController {

    private final IPointageRepository pointageRepository;

    public PointageController(IPointageRepository pointageRepository) {
        this.pointageRepository = pointageRepository;
    }

    // 🔹 Récupérer tous les pointages
    @GetMapping
    public List<Pointage> getAllPointages() {
        return pointageRepository.findAll();
    }

    // 🔹 Récupérer un pointage par ID
    @GetMapping("/{id}")
    public ResponseEntity<Pointage> getPointageById(@PathVariable Long id) {
        Optional<Pointage> pointage = pointageRepository.findById(id);
        return pointage.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 Ajouter un nouveau pointage
    @PostMapping
    public Pointage createPointage(@RequestBody Pointage pointage) {
        return pointageRepository.save(pointage);
    }

    // 🔹 Modifier un pointage existant
    @PutMapping("/{id}")
    public ResponseEntity<Pointage> updatePointage(@PathVariable Long id, @RequestBody Pointage updatedPointage) {
        return pointageRepository.findById(id)
                .map(pointage -> {
                    pointage.setDate(updatedPointage.getDate());
                    pointage.setHeureArrivee(updatedPointage.getHeureArrivee());
                    pointage.setHeureDepart(updatedPointage.getHeureDepart());
                    pointage.setType(updatedPointage.getType());
                    pointage.setEmploye(updatedPointage.getEmploye());
                    Pointage savedPointage = pointageRepository.save(pointage);
                    return ResponseEntity.ok(savedPointage);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 Supprimer un pointage
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePointage(@PathVariable Long id) {
        if (pointageRepository.existsById(id)) {
            pointageRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    // 🔹 Récupérer les pointages d’un employé spécifique
    @GetMapping("/employe/{employeId}")
    public List<Pointage> getPointagesByEmploye(@PathVariable Long employeId) {
        return pointageRepository.findByEmployeId(employeId);
    }
}
