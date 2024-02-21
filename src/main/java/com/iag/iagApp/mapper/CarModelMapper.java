package com.iag.iagApp.mapper;

import com.iag.iagApp.dto.CarModelDto;
import com.iag.iagApp.model.CarModel;

public class CarModelMapper {
    public static CarModel mapToCarModel(CarModelDto carModelDto){
        return CarModel.builder()
                .id(carModelDto.getId())
                .make(carModelDto.getMake())
                .model(carModelDto.getModel())
                .build();
    }

    public static CarModelDto mapToCarModelDto(CarModel carModel){
        return CarModelDto.builder()
                .id(carModel.getId())
                .make(carModel.getMake())
                .model(carModel.getModel())
                .build();
    }
}
