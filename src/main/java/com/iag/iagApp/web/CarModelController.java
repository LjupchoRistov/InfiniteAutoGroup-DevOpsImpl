package com.iag.iagApp.web;

import com.iag.iagApp.dto.CarModelDto;
import com.iag.iagApp.dto.OfferDto;
import com.iag.iagApp.service.CarModelService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CarModelController {
    private final CarModelService carModelService;

    public CarModelController(CarModelService carModelService) {
        this.carModelService = carModelService;
    }

    @GetMapping("/models")
    public String listCarModels(Model model){
        // Add all Car makes
        model.addAttribute("makes", carModelService.findAllDistinctMakes());

        // Add carModelDto
        model.addAttribute("carModel", new CarModelDto());

        return "car-models-new";
    }

    @PostMapping("/models/new")
    public String saveCarModel(@Valid @ModelAttribute("carModel") CarModelDto carModelDto){
        return null;
    }

    @PostMapping("/models/{id}/delete")
    public String deleteCarModels(@PathVariable String id){
        this.carModelService.deleteCarModel(Long.valueOf(id));

        return "redirect:/models";
    }
}
