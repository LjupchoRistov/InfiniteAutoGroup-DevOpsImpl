package com.iag.iagApp.dto;

import com.iag.iagApp.model.MakeEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ModelDto {
    private Long id;
    private String modelName;
    private MakeDto make;
}
