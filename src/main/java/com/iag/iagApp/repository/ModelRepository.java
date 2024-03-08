package com.iag.iagApp.repository;

import com.iag.iagApp.model.MakeEntity;
import com.iag.iagApp.model.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<ModelEntity, Long> {
    ModelEntity findByModelNameEqualsAndMakeEquals(String modelName, MakeEntity make);
}
