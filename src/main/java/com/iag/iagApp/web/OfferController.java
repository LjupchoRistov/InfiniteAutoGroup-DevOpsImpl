package com.iag.iagApp.web;

import com.iag.iagApp.dto.OfferDto;
import com.iag.iagApp.exceptions.InvalidOfferIdException;
import com.iag.iagApp.model.Offer;
import com.iag.iagApp.service.CarModelService;
import com.iag.iagApp.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        //todo: add the car of the offer
        OfferDto offerDto = this.offerService.findById(id);
        model.addAttribute("offer", offerDto);

        //todo: find cars with same make & model
        List<OfferDto> similarCars = this.offerService.findAllWithSameMakeAndModel(offerDto);
        model.addAttribute("similarCars", similarCars);

        //todo: find cars with same fuel type
        List<OfferDto> sameFuelTypeOffers = this.offerService.findAllWithSameFuelType(offerDto);
        model.addAttribute("sameFuelTypeOffers", sameFuelTypeOffers);



        return "offers-details";
    }

    @GetMapping("/layout")
    public String layout() {
        return "layout";
    }

}
