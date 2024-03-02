package com.iag.iagApp.service.impl;

import com.iag.iagApp.dto.CarModelDto;
import com.iag.iagApp.dto.OfferDto;
import com.iag.iagApp.exceptions.InvalidOfferIdException;
import com.iag.iagApp.mapper.OfferMapper;
import com.iag.iagApp.model.CarModel;
import com.iag.iagApp.model.Offer;
import com.iag.iagApp.model.UserEntity;
import com.iag.iagApp.model.enums.Style;
import com.iag.iagApp.repository.CarModelRepository;
import com.iag.iagApp.repository.OfferRepository;
import com.iag.iagApp.repository.UserRepository;
import com.iag.iagApp.service.CarModelService;
import com.iag.iagApp.service.OfferService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//todo: implement mappers
import static com.iag.iagApp.mapper.CarModelMapper.mapToCarModelDto;
import static com.iag.iagApp.mapper.OfferMapper.mapToOffer;
import static com.iag.iagApp.mapper.OfferMapper.mapToOfferDto;
import static com.iag.iagApp.mapper.CarModelMapper.mapToCarModel;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final UserRepository userRepository;
    private final CarModelRepository carModelRepository;
    private final CarModelService carModelService;

    public OfferServiceImpl(OfferRepository offerRepository, UserRepository userRepository, CarModelRepository carModelRepository, CarModelService carModelService) {
        this.offerRepository = offerRepository;
        this.userRepository = userRepository;
        this.carModelRepository = carModelRepository;
        this.carModelService = carModelService;
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
        CarModel carModel = this.carModelRepository.findByMakeEqualsAndModelEquals(offerDto.getMake(), offerDto.getModel());

        List<Offer> offerList = this.offerRepository.findAllByModelEquals(carModel);

        //todo: shuffle the list
        Collections.shuffle(offerList);
        //todo: remove the post from its own suggestions
        offerList.removeIf(offer -> offer.getId().equals(offerDto.getId()));

        return offerList.stream().map(OfferMapper::mapToOfferDto).limit(8).toList();
    }

    @Override
    public List<OfferDto> findAllWithSameMake(OfferDto offerDto) {
        CarModelDto carModelDto = mapToCarModelDto(this.carModelRepository.findByMakeEqualsAndModelEquals(offerDto.getMake(), offerDto.getModel()));
        String make = carModelDto.getMake();

        //todo: get all CarModel that contain make
        List<CarModel> carModelList = this.carModelRepository.findAllByMakeEquals(make);

        //todo: get all Offers that their CarModel is in the list
        List<Offer> offerList = new ArrayList<>();
        carModelList.forEach(carModel -> offerList.addAll(this.offerRepository.findAllByModelEquals(carModel)));

        //todo: shuffle the list
        Collections.shuffle(offerList);
        //todo: remove the post from its own suggestions
        offerList.removeIf(offer -> offer.getId().equals(offerDto.getId()));;

        return offerList.stream().map(OfferMapper::mapToOfferDto).limit(8).toList();
    }

    @Override
    public List<OfferDto> findAllWithSameStyle(OfferDto offerDto) {
        Style style = offerDto.getStyle();

        List<Offer> offerList = this.offerRepository.findAllByStyleEquals(style);

        //todo: shuffle the list
        Collections.shuffle(offerList);
        //todo: remove the post from its own suggestions
        offerList.removeIf(offer -> offer.getId().equals(offerDto.getId()));

        return offerList.stream().map(OfferMapper::mapToOfferDto).limit(8).toList();
    }

    @Override
    public List<OfferDto> findAllOffers() {
        List<Offer> offers = this.offerRepository.findAll();
        return offers.stream().map(OfferMapper::mapToOfferDto).toList();
    }

    @Override
    public Offer saveOffer(OfferDto offerDto) {
//        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findAll().getFirst();
        Offer offer = mapToOffer(offerDto, carModelService);
        offer.setCreatedBy(user);
        offer.setCoverPicture(offerDto.getPictures().getFirst());

        return this.offerRepository.save(offer);
    }

    @Override
    public void updateOffer(OfferDto offerDto) {
        Offer offer = mapToOffer(offerDto, carModelService);
        this.offerRepository.save(offer);
    }

    @Override
    public void deleteOffer(Long id) {
        this.offerRepository.deleteById(id);
    }
}
