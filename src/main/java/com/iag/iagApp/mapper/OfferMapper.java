package com.iag.iagApp.mapper;

import com.iag.iagApp.dto.CarModelDto;
import com.iag.iagApp.dto.OfferDto;
import com.iag.iagApp.model.Offer;
import com.iag.iagApp.service.CarModelService;

public class OfferMapper {

    public static Offer mapToOffer(OfferDto offerDto, CarModelService carModelService) {
        CarModelDto carModelDto = carModelService.findByMakeModel(offerDto.getMake(), offerDto.getModel());
        return Offer.builder()
                .id(offerDto.getId())
                .title(offerDto.getTitle())
                .description(offerDto.getDescription())
                .coverPicture(offerDto.getCoverPicture())
                .price(offerDto.getPrice())
                .condition(offerDto.getCondition())
                .trade(offerDto.getTrade())
                .style(offerDto.getStyle())
                .year(offerDto.getYear())
                .fuel(offerDto.getFuel())
                .transmission(offerDto.getTransmission())
                .distancePassed(offerDto.getDistancePassed())
                .driveTrain(offerDto.getDriveTrain())
                .enginePower(offerDto.getEnginePower())
                .engineLiters(offerDto.getEngineLiters())
                .engineCylinders(offerDto.getEngineCylinders())
                .engineType(offerDto.getEngineType())
                .interiorColor(offerDto.getInteriorColor())
                .exteriorColor(offerDto.getExteriorColor())
                .seats(offerDto.getSeats())
                .pictures(offerDto.getPictures())
                .createdBy(offerDto.getCreatedBy())
                .model(CarModelMapper.mapToCarModel(carModelDto))
                .build();
    }

    public static OfferDto mapToOfferDto(Offer offer) {
        return OfferDto.builder()
                .id(offer.getId())
                .title(offer.getTitle())
                .description(offer.getDescription())
                .coverPicture(offer.getCoverPicture())
                .price(offer.getPrice())
                .condition(offer.getCondition())
                .trade(offer.getTrade())
                .style(offer.getStyle())
                .year(offer.getYear())
                .fuel(offer.getFuel())
                .transmission(offer.getTransmission())
                .distancePassed(offer.getDistancePassed())
                .driveTrain(offer.getDriveTrain())
                .enginePower(offer.getEnginePower())
                .engineLiters(offer.getEngineLiters())
                .engineCylinders(offer.getEngineCylinders())
                .engineType(offer.getEngineType())
                .interiorColor(offer.getInteriorColor())
                .exteriorColor(offer.getExteriorColor())
                .seats(offer.getSeats())
                .pictures(offer.getPictures())
                .createdBy(offer.getCreatedBy())
                .make(offer.getModel().getMake())
                .model(offer.getModel().getModel())
                .build();
    }
}
