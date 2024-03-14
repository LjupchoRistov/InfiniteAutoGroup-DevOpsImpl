package com.iag.iagApp.web;

import com.iag.iagApp.dto.MakeDto;
import com.iag.iagApp.model.MakeEntity;
import com.iag.iagApp.model.ModelEntity;
import com.iag.iagApp.service.MakeService;
import com.iag.iagApp.service.ModelService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MakeController {
    private MakeService makeService;
    private ModelService modelService;

    public MakeController(MakeService makeService, ModelService modelService) {
        this.makeService = makeService;
        this.modelService = modelService;
    }

    @GetMapping("/makes")
    public String listMakes(Model model){
        // Add all makes to a list
        List<MakeDto> makeList = this.makeService.findAllMakes();
        model.addAttribute("makeList", makeList);

        // Add a template for make add
        MakeEntity make = new MakeEntity();
        model.addAttribute("make", make);

        return "makes-list";
    }

    @PostMapping("/makes/new")
    public String saveMake(@Valid @ModelAttribute("make") MakeDto makeDto,
                           BindingResult result,
                           Model model){
        if (this.makeService.isPresent(makeDto.getMakeName())){
            // Add all makes to a list
            List<MakeDto> makeList = this.makeService.findAllMakes();
            model.addAttribute("makeList", makeList);

            // Add makeDto
            model.addAttribute("make", makeDto);

            // Error message for present car make
            String errorMessage = "Car maker already present in database!"; // Replace this with your desired error message
            result.addError(new FieldError("make", "makeName", errorMessage));

            return "makes-list";
        }

        this.makeService.saveMake(makeDto);

        return "redirect:/makes";
    }

    @GetMapping("/makes/{id}/preview")
    public String previewMake(@PathVariable Long id,
                              Model model){
        // Add make with given id
        MakeDto makeDto = this.makeService.findById(id);
        model.addAttribute("make", makeDto);

        // Add all model for given make
        model.addAttribute("models", this.modelService.findAllModelsByMake(makeDto));

        // Model template
        model.addAttribute("model", new ModelEntity());

        return "makes-preview";
    }

    @PostMapping("/makes/{id}/edit")
    public String editMake(@PathVariable Long id,
                           @Valid @ModelAttribute("make") MakeDto makeDto){
        this.makeService.updateMake(makeDto);

        return "redirect:/makes/" + id + "/preview";
    }

    @GetMapping("/makes/{id}/delete")
    public String deleteMake(@PathVariable Long id){
        this.makeService.deleteMake(id);

        return "redirect:/makes";
    }

}
