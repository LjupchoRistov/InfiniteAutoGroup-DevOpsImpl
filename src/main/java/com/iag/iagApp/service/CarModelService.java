package com.iag.iagApp.service;


import com.iag.iagApp.dto.CarModelDto;
import com.iag.iagApp.dto.OfferDto;
import com.iag.iagApp.model.CarModel;
import com.iag.iagApp.model.Offer;

import java.util.List;

public interface CarModelService {
    List<CarModelDto> findAllCarModels();

    CarModelDto findById(Long id);

    List<CarModelDto> findByMake(String make);

    List<CarModelDto> findByMakeModel(String make, String model);

    CarModel saveCarModel(CarModelDto carModelDto);

    void updateCarModel(CarModelDto carModelDto);

    void deleteCarModel(Long id);
}
