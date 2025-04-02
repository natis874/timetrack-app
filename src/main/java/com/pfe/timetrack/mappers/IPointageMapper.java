package com.pfe.timetrack.mappers;

import com.pfe.timetrack.dtos.PointageDto;
import com.pfe.timetrack.models.Pointage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IPointageMapper {

    @Mapping(target = "employeId", source = "employe.id")
    PointageDto toDto(Pointage pointage);

    @Mapping(target = "employe", ignore = true)
    Pointage toEntity(PointageDto pointageDto);

    // List mapping methods
    List<PointageDto> toDtoList(List<Pointage> pointages);

    List<Pointage> toEntityList(List<PointageDto> pointageDtos);
}
