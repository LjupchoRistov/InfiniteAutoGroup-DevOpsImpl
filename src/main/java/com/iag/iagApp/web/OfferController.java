package com.iag.iagApp.web;

import com.iag.iagApp.dto.OfferDto;
import com.iag.iagApp.exceptions.InvalidOfferIdException;
import com.iag.iagApp.model.Offer;
import com.iag.iagApp.model.enums.*;
import com.iag.iagApp.repository.MakeRepository;
import com.iag.iagApp.repository.ModelRepository;
import com.iag.iagApp.service.OfferService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class OfferController {
    private final OfferService offerService;
    private final ModelRepository modelRepository;
    private final MakeRepository makeRepository;
    private static AtomicLong counter = new AtomicLong(1);

    public OfferController(OfferService offerService, ModelRepository modelRepository, MakeRepository makeRepository) {
        this.offerService = offerService;
        this.modelRepository = modelRepository;
        this.makeRepository = makeRepository;
    }

    @GetMapping({"/", "/offers"})
    public String listOffers(Model model,
                             @RequestParam(name = "filterType", required = false) String filterType,
                             @RequestParam(name = "filterAttr", required = false) String filterAttr) {

        List<OfferDto> offerDtoList;

        if (filterType != null && filterAttr != null){
            offerDtoList = this.offerService.filterOffersCategories(filterType, filterAttr);
        } else {
            offerDtoList = this.offerService.findAllOffers();
        }

        // Retrieve offers based on filter if necessary, here we're just retrieving all offers
        model.addAttribute("offers", offerDtoList);
        return "offers-list";
    }


    @GetMapping("/offers/{id}/details")
    public String offerDetails(@PathVariable Long id,
                               Model model){
        // add the offer
        OfferDto offerDto = this.offerService.findById(id);
        model.addAttribute("offer", offerDto);

        // find offers with same make & model
        List<OfferDto> sameMakeAndModelOffers = this.offerService.findAllWithSameMakeAndModel(offerDto);
        model.addAttribute("sameMakeAndModelOffers", sameMakeAndModelOffers);

        // find offers by the same make
        List<OfferDto> sameMakeOffers = this.offerService.findAllWithSameMake(offerDto);
        model.addAttribute("sameMakeOffers", sameMakeOffers);

        // find offers with the same car style
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
        model.addAttribute("makeList", this.makeRepository.findAll());

        return "offers-new";
    }

    @PostMapping("/offers/new")
    public String saveOffer(@Valid @ModelAttribute("offer") OfferDto offerDto) {
        // Save the offer
        this.offerService.saveOffer(offerDto);

        return "redirect:/offers";
    }

    @GetMapping("/offers/{id}/edit")
    public String editOffer(@PathVariable Long id,
                            Model model){
        OfferDto offerDto = this.offerService.findById(id);
        model.addAttribute("offer", offerDto);

        // Add all colors
        model.addAttribute("colors", Color.values());

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
        model.addAttribute("makeList", this.makeRepository.findAll());

        return "offers-edit";
    }

    @PostMapping("/offers/{id}/edit")
    public String updateOffer(@PathVariable Long id,
                              @Valid @ModelAttribute("offer") OfferDto offerDto){
        offerDto.setId(id);
        this.offerService.updateOffer(offerDto);

        return "redirect:/offers";
    }

    @GetMapping("/offers/{id}/delete")
    public String deleteOffer(@PathVariable("id") Long id){
        this.offerService.deleteOffer(id);

        return "redirect:/offers";
    }
}
