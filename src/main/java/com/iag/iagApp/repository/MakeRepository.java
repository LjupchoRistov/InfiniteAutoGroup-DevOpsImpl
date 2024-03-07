package com.iag.iagApp.repository;

import com.iag.iagApp.model.MakeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MakeRepository extends JpaRepository<MakeEntity, Long> {
}
