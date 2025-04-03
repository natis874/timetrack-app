package com.pfe.timetrack.controllers;

import com.pfe.timetrack.dtos.PointageDto;
import com.pfe.timetrack.exeptions.BusinessException;
import com.pfe.timetrack.mappers.IPointageMapper;
import com.pfe.timetrack.models.Employe;
import com.pfe.timetrack.models.Pointage;
import com.pfe.timetrack.repositories.IEmployeRepository;
import com.pfe.timetrack.repositories.IPointageRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pointages")
public class PointageController {

    private final IPointageRepository pointageRepository;
    private final IPointageMapper mapper;

    private final IEmployeRepository employeRepository;

    public PointageController(IPointageRepository pointageRepository, IPointageMapper mapper, IEmployeRepository employeRepository) {
        this.pointageRepository = pointageRepository;
        this.mapper = mapper;
        this.employeRepository = employeRepository;
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
    @Transactional
    public ResponseEntity<PointageDto> createPointage(@RequestBody PointageDto pointageDto) {
        try {
            // Vérification de l'existence de l'employé
            Employe employe = employeRepository.findById(pointageDto.getEmployeId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Employé non trouvé avec l'ID: " + pointageDto.getEmployeId()));

            // Vérification des contraintes métier
            if (pointageRepository.existsByEmployeIdAndDate(pointageDto.getEmployeId(), pointageDto.getDate())) {
                throw new BusinessException("Un pointage existe déjà pour cet employé à la date: " + pointageDto.getDate());
            }

            // Validation des heures
            if (pointageDto.getHeureDepart() != null &&
                    pointageDto.getHeureArrivee().isAfter(pointageDto.getHeureDepart())) {
                throw new BusinessException("L'heure d'arrivée ne peut pas être après l'heure de départ");
            }

            // Conversion et sauvegarde
            Pointage pointage = mapper.toEntity(pointageDto);
            pointage.setEmploye(employe);
            Pointage savedPointage = pointageRepository.save(pointage);

            return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(savedPointage));

        } catch (BusinessException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Une erreur technique est survenue", ex);
        }
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

    @GetMapping("/employe/{employeId}/today")
    public PointageDto getPointageDuJour(@PathVariable Long employeId) {
        List<Pointage> pointages = pointageRepository.findByEmployeIdAndDate(employeId, LocalDate.now());
        if (pointages.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Aucun pointage pour aujourd'hui");
        } else if (pointages.size() > 1) {
            throw new BusinessException("Plusieurs pointages trouvés pour le même jour veuillez contacter le support");
        } else {
            return mapper.toDto(pointages.get(0));
        }
    }
}
