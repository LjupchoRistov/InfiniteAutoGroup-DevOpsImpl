package com.iag.iagApp.service.impl;


import com.iag.iagApp.dto.CarModelDto;
import com.iag.iagApp.mapper.CarModelMapper;
import com.iag.iagApp.model.CarModel;
import com.iag.iagApp.repository.CarModelRepository;
import com.iag.iagApp.service.CarModelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//todo: implement mappers
import static com.iag.iagApp.mapper.CarModelMapper.mapToCarModel;
import static com.iag.iagApp.mapper.CarModelMapper.mapToCarModelDto;

@Service
public class CarModelServiceImpl implements CarModelService {

    private final CarModelRepository carModelRepository;

    public CarModelServiceImpl(CarModelRepository carModelRepository) {
        this.carModelRepository = carModelRepository;
    }

    public List<CarModelDto> findAllCarModels(){
        return this.carModelRepository.findAll().stream().map(CarModelMapper::mapToCarModelDto).toList();
    }

    public List<String> findAllMakes(){
        List<CarModelDto> carModelDtosList = findAllCarModels();
        return carModelDtosList.stream().map(CarModelDto::getMake).distinct().toList();
    }

    @Override
    public List<CarModelDto> findAllModelForMake(String make) {
        return findAllCarModels().stream().filter(cm -> cm.getMake().equals(make)).collect(Collectors.toList());
    }

    public CarModelDto findById(Long id){
        return mapToCarModelDto(this.carModelRepository.findById(id).get());
    }

    public List<CarModelDto> findByMake(String make){
        return this.carModelRepository.findAll().stream().filter(cm -> cm.getMake().equals(make)).map(CarModelMapper::mapToCarModelDto).toList();
    }

    @Override
    public CarModelDto findByMakeModel(String make, String model) {
        CarModel carModel = this.carModelRepository.findByMakeEqualsAndModelEquals(make, model);
        return mapToCarModelDto(carModel);
    }

    public CarModel saveCarModel(CarModelDto carModelDto){
        return this.carModelRepository.save(mapToCarModel(carModelDto));
    }

    public void updateCarModel(CarModelDto carModelDto){
        CarModel carModel = mapToCarModel(carModelDto);
        this.carModelRepository.save(carModel);
    }

    public void deleteCarModel(Long id){
        this.carModelRepository.deleteById(id);
    }
}
