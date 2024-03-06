package com.iag.iagApp.web;

import com.iag.iagApp.dto.CarModelDto;
import com.iag.iagApp.service.CarModelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CarModelRestController {

    private final CarModelService carModelService;

    public CarModelRestController(CarModelService carModelService) {
        this.carModelService = carModelService;
    }

    @GetMapping("/models/getModels")
    public List<CarModelDto> getModels(@RequestParam("make") String make) {
        return this.carModelService.findAllModelForMake(make).stream().toList();
    }
}
