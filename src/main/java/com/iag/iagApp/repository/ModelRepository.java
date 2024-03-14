package com.iag.iagApp.repository;

import com.iag.iagApp.model.MakeEntity;
import com.iag.iagApp.model.ModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<ModelEntity, Long> {
    ModelEntity findByModelNameEqualsAndMakeEquals(String modelName, MakeEntity make);

    List<ModelEntity> findAllByMakeEquals (MakeEntity make);

    ModelEntity findByModelNameEquals(String title);
}
