package com.iag.iagApp.service;

import com.iag.iagApp.dto.MakeDto;
import com.iag.iagApp.dto.ModelDto;
import com.iag.iagApp.model.ModelEntity;

import java.util.List;

public interface ModelService {
    List<ModelDto> findAllModels();
    List<ModelDto> findAllModelsByMake(MakeDto make);

    ModelEntity createModel(ModelDto modelDto, Long makeId);

    void updateModel(ModelDto modelDto);

    void deleteModel(Long id);

    ModelDto findById(Long id);

    ModelDto findByTitle(String title);
}
