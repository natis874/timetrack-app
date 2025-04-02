package com.pfe.timetrack.controllers;

import com.pfe.timetrack.dtos.PointageDto;
import com.pfe.timetrack.mappers.IPointageMapper;
import com.pfe.timetrack.models.Pointage;
import com.pfe.timetrack.repositories.IPointageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pointages")
public class PointageController {

    private final IPointageRepository pointageRepository;
    private final IPointageMapper mapper;

    public PointageController(IPointageRepository pointageRepository, IPointageMapper mapper) {
        this.pointageRepository = pointageRepository;
        this.mapper = mapper;
    }

    // 🔹 Récupérer tous les pointages
    @GetMapping
    public List<PointageDto> getAllPointages() {
        return mapper.toDtoList(pointageRepository.findAll());
    }

    // 🔹 Récupérer un pointage par ID
    @GetMapping("/{id}")
    public ResponseEntity<PointageDto> getPointageById(@PathVariable Long id) {
        Optional<Pointage> pointage = pointageRepository.findById(id);
        return pointage.map(p -> ResponseEntity.ok(mapper.toDto(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 Ajouter un nouveau pointage
    @PostMapping
    public PointageDto createPointage(@RequestBody PointageDto pointageDto) {
        Pointage pointage = mapper.toEntity(pointageDto);
        Pointage savedPointage = pointageRepository.save(pointage);
        return mapper.toDto(savedPointage);
    }

    // 🔹 Modifier un pointage existant
    @PutMapping("/{id}")
    public ResponseEntity<PointageDto> updatePointage(@PathVariable Long id, @RequestBody PointageDto updatedPointageDto) {
        return pointageRepository.findById(id)
                .map(pointage -> {
                    pointage.setDate(updatedPointageDto.getDate());
                    pointage.setHeureArrivee(updatedPointageDto.getHeureArrivee());
                    pointage.setHeureDepart(updatedPointageDto.getHeureDepart());
                    pointage.setType(updatedPointageDto.getType());
                    // Note: La gestion de l'employé devrait être traitée séparément
                    Pointage savedPointage = pointageRepository.save(pointage);
                    return ResponseEntity.ok(mapper.toDto(savedPointage));
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

    // 🔹 Récupérer les pointages d'un employé spécifique
    @GetMapping("/employe/{employeId}")
    public List<PointageDto> getPointagesByEmploye(@PathVariable Long employeId) {
        List<Pointage> pointages = pointageRepository.findByEmployeId(employeId);
        return mapper.toDtoList(pointages);
    }
}
