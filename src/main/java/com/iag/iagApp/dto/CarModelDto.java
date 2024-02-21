package com.iag.iagApp.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarModelDto {
    private Long id;
    @NotEmpty(message = "Please enter car maker!")
    private String make;
    @NotEmpty(message = "Please enter certain model!")
    private String model;
}
