package com.iag.iagApp.mapper;

import com.iag.iagApp.dto.OfferDto;
import com.iag.iagApp.model.Offer;

//todo: implement mappers
import static com.iag.iagApp.mapper.CarModelMapper.mapToCarModel;
import static com.iag.iagApp.mapper.CarModelMapper.mapToCarModelDto;

public class OfferMapper {
    public static Offer mapToOffer(OfferDto offerDto) {

        return Offer.builder()
                .id(offerDto.getId())
                .title(offerDto.getTitle())
                .description(offerDto.getDescription())
                .cover_picture(offerDto.getCover_picture())
                .price(offerDto.getPrice())
                .condition(offerDto.getCondition())
                .trade(offerDto.getTrade())
                .style(offerDto.getStyle())
                .year(offerDto.getYear())
                .fuel(offerDto.getFuel())
                .transmission(offerDto.getTransmission())
                .distance_passed(offerDto.getDistance_passed())
                .drive_train(offerDto.getDrive_train())
                .engine_power(offerDto.getEngine_power())
                .engine_liters(offerDto.getEngine_liters())
                .engine_cylinders(offerDto.getEngine_cylinders())
                .engine_type(offerDto.getEngine_type())
                .interior_color(offerDto.getInterior_color())
                .exterior_color(offerDto.getExterior_color())
                .seats(offerDto.getSeats())
                .rookie(offerDto.getRookie())
                .pictures(offerDto.getPictures())
                .createdBy(offerDto.getCreatedBy())
                .model(mapToCarModel(offerDto.getModel()))
                .build();
    }

    public static OfferDto mapToOfferDto(Offer offer) {

        return OfferDto.builder()
                .id(offer.getId())
                .title(offer.getTitle())
                .description(offer.getDescription())
                .cover_picture(offer.getCover_picture())
                .price(offer.getPrice())
                .condition(offer.getCondition())
                .trade(offer.getTrade())
                .style(offer.getStyle())
                .year(offer.getYear())
                .fuel(offer.getFuel())
                .transmission(offer.getTransmission())
                .distance_passed(offer.getDistance_passed())
                .drive_train(offer.getDrive_train())
                .engine_power(offer.getEngine_power())
                .engine_liters(offer.getEngine_liters())
                .engine_cylinders(offer.getEngine_cylinders())
                .engine_type(offer.getEngine_type())
                .interior_color(offer.getInterior_color())
                .exterior_color(offer.getExterior_color())
                .seats(offer.getSeats())
                .rookie(offer.getRookie())
                .pictures(offer.getPictures())
                .createdBy(offer.getCreatedBy())
                .model(mapToCarModelDto(offer.getModel()))
                .build();
    }
}
