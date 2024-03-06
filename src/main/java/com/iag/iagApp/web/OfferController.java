package com.iag.iagApp.web;

import com.iag.iagApp.dto.OfferDto;
import com.iag.iagApp.exceptions.InvalidOfferIdException;
import com.iag.iagApp.model.Offer;
import com.iag.iagApp.model.enums.*;
import com.iag.iagApp.service.CarModelService;
import com.iag.iagApp.service.OfferService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class OfferController {
    private final CarModelService carModelService;
    private final OfferService offerService;

    public OfferController(CarModelService carModelService, OfferService offerService) {
        this.carModelService = carModelService;
        this.offerService = offerService;
    }

    @GetMapping({"/", "/offers"})
    public String listOffers(Model model) {
        model.addAttribute("offers", offerService.findAllOffers());

        return "offers-list";
    }

    @GetMapping("/offers/{id}/details")
    public String offerDetails(@PathVariable Long id,
                               Model model) throws InvalidOfferIdException {
        //todo: add the offer
        OfferDto offerDto = this.offerService.findById(id);
        model.addAttribute("offer", offerDto);

        //todo: find offers with same make & model
        List<OfferDto> sameMakeAndModelOffers = this.offerService.findAllWithSameMakeAndModel(offerDto);
        model.addAttribute("sameMakeAndModelOffers", sameMakeAndModelOffers);

        //todo: find offers by the same make
        List<OfferDto> sameMakeOffers = this.offerService.findAllWithSameMake(offerDto);
        model.addAttribute("sameMakeOffers", sameMakeOffers);

        //todo: find offers with the same car style
        List<OfferDto> sameCarStyleOffers = this.offerService.findAllWithSameStyle(offerDto);
        model.addAttribute("sameCarStyleOffers", sameCarStyleOffers);

        return "offers-details";
    }

    @GetMapping("/layout")
    public String layout() {
        return "layout";
    }

    @GetMapping("/offers/new")
    public String addOffer(Model model) {
        Offer offer = new Offer();

        // Add offer template
        model.addAttribute("offer", offer);

        // Add all colors
        model.addAttribute("color", Color.values());

        // Add conditions
        model.addAttribute("vehicleCondition", Condition.values());

        // Add drive train types
        model.addAttribute("driveTrain", DriveTrain.values());

        // Add all engine types
        model.addAttribute("engineTypes", EngineType.values());

        // Add all fuel types
        model.addAttribute("fuelTypes", Fuel.values());

        // Add all body styles
        model.addAttribute("styles", Style.values());

        // Add all transmission types
        model.addAttribute("transmissionTypes", Transmission.values());

        // Add all vehicle makes
        model.addAttribute("makeList", this.carModelService.findAllDistinctMakes());

        //todo: engine power is entered as String, need to be converted into decimal, needs to be cheked for . REGEX(num, ., num)

        return "offers-new";
    }

    @PostMapping("/offers/new")
    public String saveOffer(@Valid @ModelAttribute("offer") OfferDto offerDto,
                            BindingResult result,
                            @RequestParam("pictures") MultipartFile[] pictures,
                            Model model) {
        if (result.hasErrors() || pictures == null || pictures.length == 0) {
            if (pictures == null || pictures.length == 0) {
                // Add a custom error message if pictures are not uploaded
                result.rejectValue("pictures", "offer.pictures.required", "Please choose at least one image.");
            }
            // If there are validation errors or no pictures uploaded, return to the form with the error messages
            return "offers-new";
        }

        // Save the offer
        this.offerService.saveOffer(offerDto);

        return "redirect:/offers";
    }

}
