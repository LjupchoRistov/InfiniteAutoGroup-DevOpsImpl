package com.iag.iagApp.service.impl;

import com.iag.iagApp.dto.ModelDto;
import com.iag.iagApp.mapper.ModelMapper;
import com.iag.iagApp.model.ModelEntity;
import com.iag.iagApp.repository.ModelRepository;
import com.iag.iagApp.service.ModelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.iag.iagApp.mapper.MakeMapper.*;
import static com.iag.iagApp.mapper.ModelMapper.*;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;

    public ModelServiceImpl(ModelRepository modelRepository) {
        this.modelRepository = modelRepository;
    }

    @Override
    public List<ModelDto> findAllModels() {
        return null;
    }

    @Override
    public ModelEntity saveModel(ModelDto modelDto) {
        return null;
    }

    @Override
    public void updateModel(ModelDto modelDto) {

    }

    @Override
    public void deleteModel(Long id) {

    }

    @Override
    public ModelDto findById(Long id) {
        return null;
    }

    @Override
    public ModelDto findByTitle(String title) {
        return null;
    }

    @Override
    public List<ModelDto> findAllModelsByMake(String make) {
        List<ModelEntity> models = this.modelRepository.findAll().stream().filter(model -> model.getMake().getMakeName().equals(make)).toList();
        return models.stream().map(ModelMapper::mapToModelDto).collect(Collectors.toList());
    }
}
