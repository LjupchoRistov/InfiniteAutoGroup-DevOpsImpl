//package com.iag.iagApp.web;
//
//import com.iag.iagApp.service.ModelService;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//public class ModelRestController {
//
//    private final ModelService modelService;
//
//    public ModelRestController(ModelService modelService) {
//        this.modelService = modelService;
//    }
//
//    @GetMapping("/models/getModels")
//    public List<CarModelDto> getModels(@RequestParam("make") String make) {
//        return this.carModelService.findAllModelForMake(make).stream().toList();
//    }
//}
