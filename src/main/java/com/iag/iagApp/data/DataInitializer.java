package com.iag.iagApp.data;

import com.iag.iagApp.mapper.OfferMapper;
import com.iag.iagApp.model.*;
import com.iag.iagApp.model.enums.*;
import com.iag.iagApp.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;


@Component
public class DataInitializer {
    private final OfferRepository offerRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final MakeRepository makeRepository;
    private final ModelRepository modelRepository;

    public DataInitializer(OfferRepository offerRepository, RoleRepository roleRepository, UserRepository userRepository, MakeRepository makeRepository, ModelRepository modelRepository) {
        this.offerRepository = offerRepository;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.makeRepository = makeRepository;
        this.modelRepository = modelRepository;
    }

    @PostConstruct
    public void initData() {
        if (this.makeRepository.findAll().isEmpty()){
            AtomicLong counter = new AtomicLong(400);
            this.makeRepository.save(new MakeEntity(counter.getAndIncrement(), "Porsche", "https://1000logos.net/wp-content/uploads/2018/02/Porsche-Logo.png"));
            this.makeRepository.save(new MakeEntity(counter.getAndIncrement(), "BMW", "https://cloud.starkinsider.com/wp-content/uploads/2020/03/1997-BMW-logo.webp"));
        }

        if (this.modelRepository.findAll().isEmpty()){
            AtomicLong counter = new AtomicLong(400);
            MakeEntity make = this.makeRepository.findByMakeNameEquals("Porsche");
            this.modelRepository.save(new ModelEntity(counter.getAndIncrement(), "911 GT3RS", make));
            this.modelRepository.save(new ModelEntity(counter.getAndIncrement(), "911 GT2RS", make));
            this.modelRepository.save(new ModelEntity(counter.getAndIncrement(), "911 GT3", make));
            this.modelRepository.save(new ModelEntity(counter.getAndIncrement(), "911 GT2", make));
            this.modelRepository.save(new ModelEntity(counter.getAndIncrement(), "911", make));
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
            MakeEntity make = this.makeRepository.findByMakeNameEquals("Porsche");
            ModelEntity model = this.modelRepository.findByModelNameEqualsAndMakeEquals("911 GT3RS", make);
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
                    model
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
                    model
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


