package com.iag.iagApp.dto;


import com.iag.iagApp.model.UserEntity;
import com.iag.iagApp.model.enums.*;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OfferDto {
    private Long id;
    // Attributes
    private String title;
    private String description;
    // First photo of the list is taken for cover photo
    private String coverPicture;
    private Long price;
    private Condition condition;
    private Boolean trade;
    private Style style;
    private Integer year;
    private Fuel fuel;
    private Integer distancePassed;
    private Transmission transmission;
    private DriveTrain driveTrain;
    private Integer enginePower;
    private String engineLiters;
    private Integer engineCylinders;
    private EngineType engineType;
    private Color interiorColor;
    private Color exteriorColor;
    private Integer seats;

    // Statisctics
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime createdOn;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime updatedOn;

    // Make and Model
    private String make;
    private String model;

    // Relations
    private List<String> pictures;
    private UserEntity createdBy;
}
