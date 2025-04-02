package com.pfe.timetrack.mappers;

import com.pfe.timetrack.dtos.PointageDto;
import com.pfe.timetrack.models.Pointage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IPointageMapper {
    IPointageMapper INSTANCE = Mappers.getMapper(IPointageMapper.class);

    @Mapping(target = "employeId", source = "employe.id")
    PointageDto toDto(Pointage pointage);

    @Mapping(target = "employe", ignore = true)
    Pointage toDao(PointageDto pointageDto);

    // List mapping methods
    List<PointageDto> toDtos(List<Pointage> pointages);

    List<Pointage> toDaos(List<PointageDto> pointageDtos);
}
