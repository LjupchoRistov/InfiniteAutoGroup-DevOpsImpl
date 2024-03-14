package com.iag.iagApp.web;

import com.iag.iagApp.dto.MakeDto;
import com.iag.iagApp.dto.ModelDto;
import com.iag.iagApp.service.MakeService;
import com.iag.iagApp.service.ModelService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ModelController {
    private final ModelService modelService;
    private final MakeService makeService;

    public ModelController(ModelService modelService, MakeService makeService) {
        this.modelService = modelService;
        this.makeService = makeService;
    }

    @PostMapping("/models/{makeId}/new")
    public String createModel(@PathVariable("makeId") Long makeId,
                              @Valid @ModelAttribute("model") ModelDto modelDto) {
        this.modelService.createModel(modelDto, makeId);

        return "redirect:/makes/" + modelDto.getMake().getId() + "/preview";
    }

    @GetMapping("/models/{modelId}/delete")
    public String deleteModel(@PathVariable("modelId") Long modelId){
        // Get model
        ModelDto modelDto = this.modelService.findById(modelId);

        // Get make id
        Long makeId = modelDto.getMake().getId();

        // Delete model
        this.modelService.deleteModel(modelId);

        return "redirect:/makes/" + makeId + "/preview";
    }
}
