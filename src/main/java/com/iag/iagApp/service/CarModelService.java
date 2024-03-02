package com.iag.iagApp.service;


import com.iag.iagApp.dto.CarModelDto;
import com.iag.iagApp.model.CarModel;

import java.util.List;

public interface CarModelService {
    List<CarModelDto> findAllCarModels();
    List<String> findAllMakes();

    List<CarModelDto> findAllModelForMake(String make);

    CarModelDto findById(Long id);

    List<CarModelDto> findByMake(String make);

    CarModelDto findByMakeModel(String make, String model);

    CarModel saveCarModel(CarModelDto carModelDto);

    void updateCarModel(CarModelDto carModelDto);

    void deleteCarModel(Long id);
}
