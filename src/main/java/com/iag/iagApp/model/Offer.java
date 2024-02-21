package com.iag.iagApp.model;

import com.iag.iagApp.model.enums.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "offer")
public class Offer {
    //todo: table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //todo: attributes
    private String title;
//    @Lob // Indicates that the description field should be mapped to a large object type
    private String description;
    private String cover_picture;
    private Long price;
    @Enumerated(value = EnumType.STRING)
    private Condition condition;
    private Boolean trade;
    @Enumerated(value = EnumType.STRING)
    private Style style;
    private Integer year;
    @Enumerated(value = EnumType.STRING)
    private Fuel fuel;
    private Long distance_passed;
    @Enumerated(value = EnumType.STRING)
    private Transmission transmission;
    @Enumerated(value = EnumType.STRING)
    private DriveTrain drive_train;
    private Integer engine_power;
    private Float engine_liters;
    private Integer engine_cylinders;
    @Enumerated(value = EnumType.STRING)
    private EngineType engine_type;
    @Enumerated(value = EnumType.STRING)
    private Color interior_color;
    @Enumerated(value = EnumType.STRING)
    private Color exterior_color;
    private Integer seats;
    private Boolean rookie;

    //todo: statisctics
    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;

    //todo: relations
    @ElementCollection
    @CollectionTable(name = "offer_pictures", joinColumns = @JoinColumn(name = "offer_id"))
    private List<String> pictures;
    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private UserEntity createdBy;
    @ManyToOne
    @JoinColumn(name = "car_make_model", nullable = false)
    private CarModel model;
}