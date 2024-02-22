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

}
