package com.iag.iagApp.service;

import com.iag.iagApp.dto.ModelDto;
import com.iag.iagApp.dto.OfferDto;
import com.iag.iagApp.exceptions.InvalidOfferIdException;
import com.iag.iagApp.model.ModelEntity;
import com.iag.iagApp.model.Offer;

import java.util.List;

public interface ModelService {
    List<ModelDto> findAllModels();

    ModelEntity saveModel(ModelDto modelDto);

    void updateModel(ModelDto modelDto);

    void deleteModel(Long id);

    ModelDto findById(Long id);

    ModelDto findByTitle(String title);

    List<ModelDto> findAllModelsByMake(String make);
}
