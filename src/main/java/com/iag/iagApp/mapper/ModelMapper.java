package com.iag.iagApp.mapper;

import com.iag.iagApp.dto.ModelDto;
import com.iag.iagApp.model.ModelEntity;

import static com.iag.iagApp.mapper.MakeMapper.*;

public class ModelMapper {
    public static ModelEntity mapToModel(ModelDto modelDto){
        return ModelEntity.builder()
                .id(modelDto.getId())
                .modelName(modelDto.getModelName())
                .make(mapToMake(modelDto.getMake()))// need to map MakeDto to MakeEntity
                .build();
    }

    public static ModelDto mapToModelDto(ModelEntity model){
        return ModelDto.builder()
                .id(model.getId())
                .modelName(model.getModelName())
                .make(mapToMakeDto(model.getMake()))// need to map MakeEntity to MakeDto
                .build();
    }
}
