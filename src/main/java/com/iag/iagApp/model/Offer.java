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
    private String coverPicture;
    private Long price;
    @Enumerated(value = EnumType.STRING)
    private Condition condition;
    private Boolean trade;
    @Enumerated(value = EnumType.STRING)
    private Style style;
    private Integer year;
    @Enumerated(value = EnumType.STRING)
    private Fuel fuel;
    private Integer distancePassed;
    @Enumerated(value = EnumType.STRING)
    private Transmission transmission;
    @Enumerated(value = EnumType.STRING)
    private DriveTrain driveTrain;
    private Integer enginePower;
    private String engineLiters;
    private Integer engineCylinders;
    @Enumerated(value = EnumType.STRING)
    private EngineType engineType;
    @Enumerated(value = EnumType.STRING)
    private Color interiorColor;
    @Enumerated(value = EnumType.STRING)
    private Color exteriorColor;
    private Integer seats;

    // Statisctics
    @CreationTimestamp
    private LocalDateTime createdOn;
    @UpdateTimestamp
    private LocalDateTime updatedOn;

    // Relations
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