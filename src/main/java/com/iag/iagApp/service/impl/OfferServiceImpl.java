package com.iag.iagApp.service.impl;

import com.iag.iagApp.dto.CarModelDto;
import com.iag.iagApp.dto.OfferDto;
import com.iag.iagApp.exceptions.InvalidOfferIdException;
import com.iag.iagApp.mapper.OfferMapper;
import com.iag.iagApp.model.CarModel;
import com.iag.iagApp.model.Offer;
import com.iag.iagApp.model.UserEntity;
import com.iag.iagApp.model.enums.Fuel;
import com.iag.iagApp.model.enums.Style;
import com.iag.iagApp.repository.OfferRepository;
import com.iag.iagApp.repository.UserRepository;
import com.iag.iagApp.service.OfferService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//todo: implement mappers
import static com.iag.iagApp.mapper.OfferMapper.mapToOffer;
import static com.iag.iagApp.mapper.OfferMapper.mapToOfferDto;
import static com.iag.iagApp.mapper.CarModelMapper.mapToCarModel;
import static com.iag.iagApp.mapper.CarModelMapper.mapToCarModelDto;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final UserRepository userRepository;

    public OfferServiceImpl(OfferRepository offerRepository, UserRepository userRepository) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public OfferDto findById(Long id) throws InvalidOfferIdException {
        return mapToOfferDto(this.offerRepository.findById(id).orElseThrow(InvalidOfferIdException::new));
    }

    @Override
    public OfferDto findByTitle(String title) {
        return mapToOfferDto(this.offerRepository.findByTitle(title));
    }

    @Override
    public List<OfferDto> findAllWithSameMakeAndModel(OfferDto offerDto) {
        CarModel carModel = mapToCarModel(offerDto.getModel());

        List<Offer> offers = offerRepository.findAllByModelEquals(carModel);
        offers.removeIf(offer -> offer.getId().equals(offerDto.getId()));

        return offers.stream()
                .map(OfferMapper::mapToOfferDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OfferDto> findAllWithSameFuelType(OfferDto offerDto) {
        Fuel fuel = offerDto.getFuel();

        List<Offer> offers = this.offerRepository.findAllByFuelEquals(fuel);
        offers.removeIf(offer -> offer.getId().equals(offerDto.getId()));

        return offers.stream().map(OfferMapper::mapToOfferDto).toList();
    }

    @Override
    public List<OfferDto> findAllWithSameStyle(OfferDto offerDto) {
        Style style = offerDto.getStyle();

        List<Offer> offers = this.offerRepository.findAllByStyleEquals(style);
        offers.removeIf(offer -> offer.getId().equals(offerDto.getId()));

        return offers.stream().map(OfferMapper::mapToOfferDto).toList();
    }

    @Override
    public List<OfferDto> findAllOffers() {
        List<Offer> offers = this.offerRepository.findAll();
        return offers.stream().map(OfferMapper::mapToOfferDto).toList();
    }

    @Override
    public Offer saveOffer(OfferDto offerDto) {
//        String username = SecurityUtil.getSessionUser();
        String username = "test_user";
        UserEntity user = userRepository.findByUsername(username);
        Offer offer = mapToOffer(offerDto);
        offer.setCreatedBy(user);

        return this.offerRepository.save(offer);
    }

    @Override
    public void updateOffer(OfferDto offerDto) {
        Offer offer = mapToOffer(offerDto);
        this.offerRepository.save(offer);
    }

    @Override
    public void deleteOffer(Long id) {
        this.offerRepository.deleteById(id);
    }
}
