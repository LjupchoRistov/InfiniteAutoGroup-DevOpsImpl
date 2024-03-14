package com.iag.iagApp.service;

import com.iag.iagApp.dto.OfferDto;
import com.iag.iagApp.exceptions.InvalidOfferIdException;

import java.util.List;

public interface OfferService {
    List<OfferDto> findAllOffers();

    void saveOffer(OfferDto offerDto);

    void updateOffer(OfferDto offerDto);

    void deleteOffer(Long id);

    OfferDto findById(Long id);

    OfferDto findByTitle(String title);

    List<OfferDto> findAllWithSameMakeAndModel(OfferDto offerDto);

    List<OfferDto> findAllWithSameMake(OfferDto offerDto);

    List<OfferDto> findAllWithSameStyle(OfferDto offerDto);

    List<OfferDto> filterOffersCategories(String filterType, String filterAttr);
}
