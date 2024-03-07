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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class OfferController {
    private final CarModelService carModelService;
    private final OfferService offerService;
    private static AtomicLong counter = new AtomicLong(1);

    public OfferController(CarModelService carModelService, OfferService offerService) {
        this.carModelService = carModelService;
        this.offerService = offerService;
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
        Offer offer = populateOffer();

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
    public String saveOffer(@Valid @ModelAttribute("offer") OfferDto offerDto) {
        // Save the offer
        this.offerService.saveOffer(offerDto);

        return "redirect:/offers";
    }

    private Offer populateOffer() {
        Offer offer = new Offer();

        offer.setTitle("test num:" + counter.get());
        offer.setDescription("test desc num: " + counter.get());
        offer.setPrice(counter.get());
        offer.setCondition(Condition.USED);
        offer.setDistancePassed((int) (10000 * counter.get()));
        offer.setTrade(false);
        offer.setStyle(Style.COUPE);
        offer.setYear((int) (2000 + counter.get()));
        offer.setFuel(Fuel.PETROL);
        offer.setTransmission(Transmission.AUTOMATIC);
        offer.setDriveTrain(DriveTrain.RWD);
        offer.setEnginePower((int) (50 * counter.get()));
        offer.setEngineLiters("3.0");
        offer.setEngineCylinders((int) counter.get());
        offer.setEngineType(EngineType.F6);
        offer.setExteriorColor(Color.BLACK);
        offer.setInteriorColor(Color.BLACK);
        offer.setSeats(2);

        return offer;
    }
}
