package com.iag.iagApp.dto;


import com.iag.iagApp.model.CarModel;
import com.iag.iagApp.model.UserEntity;
import com.iag.iagApp.model.enums.*;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OfferDto {
    private Long id;
    // Attributes
//    @NotEmpty(message = "Please enter title")
    private String title;
//    @NotEmpty(message = "Please enter description!")
    private String description;
    // Must be empty
    // First photo of the list is taken for cover photo
    private String coverPicture;
//    @NotEmpty(message = "Please enter price!")
    private Long price;
//    @NotEmpty(message = "Please enter trade!")
    private Condition condition;
//    @NotEmpty(message = "Please enter trade!")
    private Boolean trade;
//    @NotEmpty(message = "Please enter style!")
    private Style style;
//    @NotEmpty(message = "Please enter year!")
    private Integer year;
//    @NotEmpty(message = "Please enter fuel!")
    private Fuel fuel;
//    @NotEmpty(message = "Please enter distancePassed!")
    private Integer distancePassed;
//    @NotEmpty(message = "Please enter transmission!")
    private Transmission transmission;
//    @NotEmpty(message = "Please enter drive train!")
    private DriveTrain driveTrain;
//    @NotEmpty(message = "Please enter engine power!")
    private Integer enginePower;
//    @NotEmpty(message = "Please enter engine liters!")
    private String engineLiters;
//    @NotEmpty(message = "Please enter number of engine cylinders!")
    private Integer engineCylinders;
    private EngineType engineType;
//    @NotEmpty(message = "Please enter interior color!")
    private Color interiorColor;
//    @NotEmpty(message = "Please enter exterior color!")
    private Color exteriorColor;
//    @NotEmpty(message = "Please enter number of seats!")
    private Integer seats;

    // Statisctics
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime createdOn;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime updatedOn;

    // Make and Model
//    @NotEmpty(message = "Please enter number of seats!")
    private String make;
//    @NotEmpty(message = "Please enter number of seats!")
    private String model;

    // Relations
    private List<String> pictures;
    private UserEntity createdBy;
}
