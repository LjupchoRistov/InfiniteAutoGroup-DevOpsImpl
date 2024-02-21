package com.iag.iagApp.dto;


import com.iag.iagApp.model.CarModel;
import com.iag.iagApp.model.UserEntity;
import com.iag.iagApp.model.enums.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OfferDto {
    private Long id;
    //todo: attributes
    @NotEmpty(message = "Title cannot be empty!")
    private String title;
    @NotEmpty(message = "Description cannot be empty!")
    private String description;
    private String cover_picture;
    @NotEmpty(message = "Price cannot be empty!")
    private Long price;
    @NotEmpty(message = "Condition cannot be empty!")
    private Condition condition;
    @NotEmpty(message = "Please select yes/no!")
    private Boolean trade;
    @NotEmpty(message = "Style cannot be empty!")
    private Style style;
    @NotEmpty(message = "Year make cannot be empty!")
    private Integer year;
    @NotEmpty(message = "Fuel type cannot be empty!")
    private Fuel fuel;
    @NotEmpty(message = "... cannot be empty!")
    private Long distance_passed;
    @NotEmpty(message = "Transmission type cannot be empty!")
    private Transmission transmission;
    @NotEmpty(message = "Engine power cannot be empty!")
    private DriveTrain drive_train;
    private Integer engine_power;
    private Float engine_liters;
    private Integer engine_cylinders;
    private EngineType engine_type;
    private Color interior_color;
    private Color exterior_color;
    @NotEmpty(message = "Number of seats cannot be empty!")
    private Integer seats;
    @NotEmpty(message = "Please select yes/no!")
    private Boolean rookie;

    //todo: relations
    private List<String> pictures;
    private UserEntity createdBy;
    private CarModelDto model;
}
