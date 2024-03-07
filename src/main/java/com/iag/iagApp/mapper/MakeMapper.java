package com.iag.iagApp.mapper;

import com.iag.iagApp.dto.MakeDto;
import com.iag.iagApp.model.MakeEntity;

public class MakeMapper {
    public static MakeEntity mapToMake(MakeDto makeDto){
        return MakeEntity.builder()
                .id(makeDto.getId())
                .make(makeDto.getMake())
                .build();
    }

    public static MakeDto mapToMakeDto(MakeEntity make){
        return MakeDto.builder()
                .id(make.getId())
                .make(make.getMake())
                .build();
    }
}
