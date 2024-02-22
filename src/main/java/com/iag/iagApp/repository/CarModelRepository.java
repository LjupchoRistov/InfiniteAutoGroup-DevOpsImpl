package com.iag.iagApp.repository;

import com.iag.iagApp.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarModelRepository extends JpaRepository<CarModel, Long> {
    List<CarModel> findAllByMakeEquals(String make);
    CarModel findByMakeEqualsAndModelEquals(String make, String model);
}
