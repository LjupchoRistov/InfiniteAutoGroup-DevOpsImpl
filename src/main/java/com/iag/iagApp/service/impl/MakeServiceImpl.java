package com.iag.iagApp.service.impl;

import com.iag.iagApp.dto.MakeDto;
import com.iag.iagApp.mapper.MakeMapper;
import com.iag.iagApp.model.MakeEntity;
import com.iag.iagApp.model.ModelEntity;
import com.iag.iagApp.model.Offer;
import com.iag.iagApp.repository.MakeRepository;
import com.iag.iagApp.repository.ModelRepository;
import com.iag.iagApp.repository.OfferRepository;
import com.iag.iagApp.service.MakeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.iag.iagApp.mapper.MakeMapper.*;

@Service
public class MakeServiceImpl implements MakeService {

    private final MakeRepository makeRepository;
    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;

    public MakeServiceImpl(MakeRepository makeRepository, OfferRepository offerRepository, ModelRepository modelRepository) {
        this.makeRepository = makeRepository;
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public List<MakeDto> findAllMakes() {
        // Get all makes
        List<MakeDto> makeEntities = this.makeRepository.findAll().stream().map(MakeMapper::mapToMakeDto).toList();

        // Offers count
        for (MakeDto make : makeEntities) {
            int offersCount = 0;
            // Get all models from the given make
            List<ModelEntity> models = this.modelRepository.findAllByMakeEquals(mapToMake(make));
            List<Offer> offers = new ArrayList<>();
            for (ModelEntity model : models) {
                // Count all the offers containing the given model
                int count = this.offerRepository.findAllByModelEquals(model).size();
                offersCount += count;
            }

            make.setOffersCount(offersCount);
        }

        // Models count
        for (MakeDto make : makeEntities) {
            Integer counter = this.modelRepository.findAllByMakeEquals(mapToMake(make)).size();

            make.setModelCount(counter);
        }

        return makeEntities;
    }

    @Override
    public MakeEntity saveMake(MakeDto makeDto) {
        return this.makeRepository.save(mapToMake(makeDto));
    }

    @Override
    public void updateMake(MakeDto makeDto) {
        this.makeRepository.save(mapToMake(makeDto));
    }

    @Override
    public void deleteMake(Long id) {
        this.makeRepository.delete(this.makeRepository.findById(id).get());
    }

    @Override
    public MakeDto findById(Long id) {
        return mapToMakeDto(this.makeRepository.findById(id).get());
    }

    @Override
    public MakeDto findByTitle(String title) {
        return mapToMakeDto(this.makeRepository.findByMakeNameEquals(title));
    }

    @Override
    public boolean isPresent(String makeName) {
        Optional<MakeEntity> make = Optional.ofNullable(this.makeRepository.findByMakeNameEquals(makeName));
        return make.isPresent();
    }
}
