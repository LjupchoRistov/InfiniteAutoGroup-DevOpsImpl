package com.iag.iagApp.data;

import com.iag.iagApp.mapper.OfferMapper;
import com.iag.iagApp.model.CarModel;
import com.iag.iagApp.model.Offer;
import com.iag.iagApp.model.Role;
import com.iag.iagApp.model.UserEntity;
import com.iag.iagApp.model.enums.*;
import com.iag.iagApp.repository.CarModelRepository;
import com.iag.iagApp.repository.OfferRepository;
import com.iag.iagApp.repository.RoleRepository;
import com.iag.iagApp.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Component
public class DataInitializer {
    private final CarModelRepository carModelRepository;
    private final OfferRepository offerRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public DataInitializer(CarModelRepository carModelRepository, OfferRepository offerRepository, RoleRepository roleRepository, UserRepository userRepository) {
        this.carModelRepository = carModelRepository;
        this.offerRepository = offerRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void initData() {
        if (this.carModelRepository.findAll().isEmpty()){
            CarModel carModel1 = new CarModel((long)999, "Porsche", "911 GT3 RS");
            CarModel carModel2 = new CarModel((long)998, "Porsche", "911 GT2 RS");
            CarModel carModel3 = new CarModel((long)997, "Porsche", "911");

            this.carModelRepository.save(carModel1);
            this.carModelRepository.save(carModel2);
            this.carModelRepository.save(carModel3);
        }

        if (this.roleRepository.findAll().isEmpty()) {
            this.roleRepository.save(new Role((long) 0, "ADMIN", null));
            this.roleRepository.save(new Role((long) 0, "EDITOR", null));
            this.roleRepository.save(new Role((long) 0, "USER", null));
        }

        if (this.userRepository.findAll().isEmpty()) {
            Role userRole = this.roleRepository.findByName("USER");
            List<Role> roles = new ArrayList<>();
            roles.add(userRole);
            for (int i = 0; i < 5; i++) {
                UserEntity user = new UserEntity((long) 999 - i, "username" + i, "email" + i, "password" + i, roles);
                this.userRepository.save(user);
            }
        }

        if (this.offerRepository.findAll().isEmpty()) {
            List<String> pictures = getPictures1();
            CarModel carModel = this.carModelRepository.findByMakeEqualsAndModelEquals("Porsche", "911 GT3 RS");
            Offer offer1 = new Offer(
                    (long) 999,
                    "2019 Porsche 911 GT3 RS",
                    "The Porsche 911 GT3 RS is a high-performance variant of the iconic Porsche 911 sports car. It's designed for track-focused driving enthusiasts seeking an adrenaline-filled experience.",
                    "https://d2blhp03rkvfi4.cloudfront.net/cache/width_916/car/car-286083/photo/2019-porsche-911--gt3--rs-247900-281669118.webp",
                    (long) 247900,
                    Condition.USED,
                    false,
                    Style.COUPE,
                    2019,
                    Fuel.PETROL,
                     7890,
                    Transmission.AUTOMATIC,
                    DriveTrain.RWD,
                    520,
                    "4.0",
                    6,
                    EngineType.F6,
                    Color.BLACK,
                    Color.BLUE,
                    2,
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    pictures,
                    this.userRepository.findAll().getLast(),
                    carModel
            );
            offerRepository.save(offer1); // Save the offer to the database

            pictures.clear();
            pictures = getPictures2();
            Offer offer2 = new Offer(
                    (long) 998,
                    "2024 Porsche 911 GT3 RS",
                    "The Porsche 911 GT3 RS is a high-performance variant of the iconic Porsche 911 sports car. It's designed for track-focused driving enthusiasts seeking an adrenaline-filled experience.",
                    "https://d2blhp03rkvfi4.cloudfront.net/cache/width_916/car/car-287690/photo/2024-porsche-911--gt3--rs-510992-63342772.webp",
                    (long) 510992,
                    Condition.USED,
                    false,
                    Style.COUPE,
                    2024,
                    Fuel.PETROL,
                     55,
                    Transmission.AUTOMATIC,
                    DriveTrain.RWD,
                    520,
                    "4.0",
                    6,
                    EngineType.F6,
                    Color.BLACK,
                    Color.BLUE,
                    2,
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    pictures,
                    this.userRepository.findAll().getLast(),
                    carModel
            );
            offerRepository.save(offer2); // Save the offer to the database
        }
    }

    private static List<String> getPictures1() {
        List<String> pictures = new ArrayList<>();
        pictures.add("https://d2blhp03rkvfi4.cloudfront.net/cache/width_916/car/car-286083/photo/2019-porsche-911--gt3--rs-247900-281669118.webp");
        pictures.add("https://d2blhp03rkvfi4.cloudfront.net/cache/width_916/car/car-286083/photo/2019-porsche-911--gt3--rs-247900-323487631.webp");
        pictures.add("https://d2blhp03rkvfi4.cloudfront.net/cache/width_916/car/car-286083/photo/2019-porsche-911--gt3--rs-247900-281669118.webp");
        pictures.add("https://d2blhp03rkvfi4.cloudfront.net/cache/width_916/car/car-286083/photo/2019-porsche-911--gt3--rs-247900-1389457299.webp");
        pictures.add("https://d2blhp03rkvfi4.cloudfront.net/cache/width_916/car/car-286083/photo/2019-porsche-911--gt3--rs-247900-279999327.webp");
        pictures.add("https://d2blhp03rkvfi4.cloudfront.net/cache/width_916/car/car-286083/photo/2019-porsche-911--gt3--rs-247900-430896769.webp");
        return pictures;
    }
    private static List<String> getPictures2() {
        List<String> pictures = new ArrayList<>();
        pictures.add("https://d2blhp03rkvfi4.cloudfront.net/cache/width_916/car/car-287690/photo/2024-porsche-911--gt3--rs-510992-63342772.webp");
        pictures.add("https://d2blhp03rkvfi4.cloudfront.net/cache/width_916/car/car-287690/photo/2024-porsche-911--gt3--rs-510992-2130898117.webp");
        pictures.add("https://d2blhp03rkvfi4.cloudfront.net/cache/width_916/car/car-287690/photo/2024-porsche-911--gt3--rs-510992-913379083.webp");
        pictures.add("https://d2blhp03rkvfi4.cloudfront.net/cache/width_916/car/car-287690/photo/2024-porsche-911--gt3--rs-510992-1645378464.webp");
        pictures.add("https://d2blhp03rkvfi4.cloudfront.net/cache/width_916/car/car-287690/photo/2024-porsche-911--gt3--rs-510992-163991229.webp");
        return pictures;
    }
}


