package com.pfe.timetrack.mappers;

import com.pfe.timetrack.dtos.EmployeDto;
import com.pfe.timetrack.models.Employe;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IEmployeMapper {

    @Mapping(target = "managerId", source = "mananger.id")
    EmployeDto toDto(Employe employe);

    @Mapping(target = "mananger", ignore = true) // Géré séparément
    Employe toEntity(EmployeDto employeDto);

    // Méthodes pour les listes
    List<EmployeDto> toDtoList(List<Employe> employes);
    List<Employe> toEntityList(List<EmployeDto> employeDtos);
}
