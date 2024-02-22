package com.iag.iagApp.repository;

import com.iag.iagApp.model.CarModel;
import com.iag.iagApp.model.Offer;
import com.iag.iagApp.model.enums.Fuel;
import com.iag.iagApp.model.enums.Style;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
    Offer findByTitle(String title);
    List<Offer> findAllByModelEquals(CarModel model);
    List<Offer> findAllByStyleEquals(Style style);
}
