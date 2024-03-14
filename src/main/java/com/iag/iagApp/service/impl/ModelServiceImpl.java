package com.iag.iagApp.service.impl;

import com.iag.iagApp.dto.MakeDto;
import com.iag.iagApp.dto.ModelDto;
import com.iag.iagApp.mapper.ModelMapper;
import com.iag.iagApp.model.MakeEntity;
import com.iag.iagApp.model.ModelEntity;
import com.iag.iagApp.repository.MakeRepository;
import com.iag.iagApp.repository.ModelRepository;
import com.iag.iagApp.repository.OfferRepository;
import com.iag.iagApp.service.ModelService;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

import static com.iag.iagApp.mapper.MakeMapper.*;
import static com.iag.iagApp.mapper.ModelMapper.*;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    private final OfferRepository offerRepository;
    private final MakeRepository makeRepository;

    public ModelServiceImpl(ModelRepository modelRepository, OfferRepository offerRepository, MakeRepository makeRepository) {
        this.modelRepository = modelRepository;
        this.offerRepository = offerRepository;
        this.makeRepository = makeRepository;
    }

    @Override
    public List<ModelDto> findAllModels() {
        List<ModelDto> modelDtos = this.modelRepository.findAll().stream().map(ModelMapper::mapToModelDto).toList();

        // Insert the counter
        modelDtos.stream().map(this::insertCounter).collect(Collectors.toList());

        return modelDtos;
    }

    @Override
    public ModelEntity createModel(ModelDto modelDto, Long makeId) {
        MakeDto makeDto = mapToMakeDto(this.makeRepository.findById(makeId).get());
        modelDto.setMake(makeDto);
        ModelEntity model = mapToModel(modelDto);

        return this.modelRepository.save(model);
    }

    @Override
    public void updateModel(ModelDto modelDto) {
        ModelEntity model = mapToModel(modelDto);

        this.modelRepository.save(model);
    }

    @Override
    public void deleteModel(Long id) {
        ModelEntity model = this.modelRepository.findById(id).get();

        this.modelRepository.delete(model);
    }

    @Override
    public ModelDto findById(Long id) {
        ModelEntity model = this.modelRepository.findById(id).get();

        return mapToModelDto(model);
    }

    @Override
    public ModelDto findByTitle(String title) {
        ModelEntity model = this.modelRepository.findByModelNameEquals(title);

        return mapToModelDto(model);
    }

    @Override
    public List<ModelDto> findAllModelsByMake(MakeDto make) {
        List<ModelDto> modelDtos = this.modelRepository.findAllByMakeEquals(mapToMake(make)).stream().map(ModelMapper::mapToModelDto).toList();

        // Insert the counter
        modelDtos.stream().map(this::insertCounter).collect(Collectors.toList());

        return modelDtos;
    }

    private ModelDto insertCounter(ModelDto model) {
        // Count all offers for every model
        int count = this.offerRepository.findAllByModelEquals(mapToModel(model)).size();
        model.setOffersCount(count);

        return model;
    }
}
