package com.pfe.timetrack.mappers;

import com.pfe.timetrack.dtos.EmployeDto;
import com.pfe.timetrack.models.Employe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = IPointageMapper.class)
public interface IEmployeMapper {

    @Mapping(target = "managerId", source = "manager.id")
    @Mapping(target = "pointages", source = "pointages")
    EmployeDto toDto(Employe employe);

    @Mapping(target = "manager", ignore = true)
        // Géré séparément
    Employe toEntity(EmployeDto employeDto);

    // Méthodes pour les listes
    List<EmployeDto> toDtoList(List<Employe> employes);

    List<Employe> toEntityList(List<EmployeDto> employeDtos);
}
