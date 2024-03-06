package com.iag.iagApp.web;


import com.iag.iagApp.dto.CarModelDto;
import com.iag.iagApp.model.enums.EngineType;
import com.iag.iagApp.service.CarModelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OfferRestController {

    private final CarModelService carModelService;

    public OfferRestController(CarModelService carModelService) {
        this.carModelService = carModelService;
    }

    @GetMapping("/offers/getEngineTypes")
    public List<String> getEngineTypes(@RequestParam("cylinders") String cylindersStr) {
        if (cylindersStr.equalsIgnoreCase("0")) {
            List<String> targetEngineTypes = new ArrayList<>();
            targetEngineTypes.add("ROTARY");
            return targetEngineTypes;
        } else {
            // Get all engine types
            List<String> engineTypesList = Arrays.stream(EngineType.values())
                    .map(Enum::name)
                    .toList();
            int cylinders = Integer.parseInt(cylindersStr);
            // Filter engine types based on the number of cylinders
            List<String> filteredEngineTypes = new ArrayList<>();
            for (String engineType : engineTypesList) {
                if (engineType.contains(String.valueOf(cylinders))) {
                    filteredEngineTypes.add(engineType);
                }
                if (cylinders == 2 || cylinders == 6) {
                    filteredEngineTypes.removeIf(e -> e.contains("1"));
                }
            }
            return filteredEngineTypes;
        }
    }

    @GetMapping("/offers/getModels")
    public List<String> getModels(@RequestParam("make") String make) {
        return this.carModelService.findAllModelForMake(make).stream().map(CarModelDto::getModel).toList();
    }

}