package com.iag.iagApp.service;

import com.iag.iagApp.dto.MakeDto;
import com.iag.iagApp.dto.ModelDto;
import com.iag.iagApp.model.MakeEntity;
import com.iag.iagApp.model.ModelEntity;

import java.util.List;

public interface MakeService {
    List<MakeDto> findAllMakes();

    MakeEntity saveMake(MakeDto makeDto);

    void updateMake(MakeDto makeDto);

    void deleteMake(Long id);

    MakeDto findById(Long id);

    MakeDto findByTitle(String title);

    boolean isPresent(String makeName);
}
