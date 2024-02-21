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
        CarModel carModel1 = new CarModel((long)999, "Porsche", "911 GT3 RS");
        CarModel carModel2 = new CarModel((long)999, "Porsche", "911 GT2 RS");
        CarModel carModel3 = new CarModel((long)999, "Porsche", "911");

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
            Offer offer1 = new Offer(
                    (long) 999,
                    "2019 Porsche 911 GT3 RS",
                    "2019  Slats Painted and Air Vent ther, Chrono  with Preparation for Lap Trigger, Door Handles in High Gloss Black, Extended Range Fuel Tank, 23.7 gal, Front Axle Lift System, Headlight Cleaning System Covers in Deviated Exterior Color, LED Headlights in Black with Porsche Dynamic Light System (PDLS), Lower Trim of SportDesign Exterior Mirrors in High Gloss Black, Miami Blue, Seat Belts in Miami Blue, Steering Wheel and Gear Lever/Selector in Black Leather.",
                    "https://d2blhp03rkvfi4.cloudfront.net/cache/width_916/car/car-286083/photo/2019-porsche-911--gt3--rs-247900-281669118.webp",
                    (long) 247900,
                    Condition.USED,
                    false,
                    Style.COUPE,
                    2019,
                    Fuel.PETROL,
                    (long) 7890,
                    Transmission.AUTOMATIC,
                    DriveTrain.RWD,
                    520,
                    (float) 4,
                    6,
                    EngineType.F6,
                    Color.BLACK,
                    Color.BLUE,
                    2,
                    false,
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    pictures,
                    this.userRepository.findAll().getLast(),
                    carModel1
            );
            offerRepository.save(offer1); // Save the offer to the database

            pictures.clear();
            pictures = getPictures2();
            Offer offer2 = new Offer(
                    (long) 998,
                    "2024 Porsche 911 GT3 RS",
                    "Located at The Collection at 200 Bird Rd, Coral Gables, FL 33146. Please contact our Pre-owned Sales Manager, Manny Sotomayor at *number in the description* , for more information. The Collection Is Not Responsible for Typographical Errors.PORSCHE CERTIFIED PRE-OWNED This Certified Porsche includes a 111 point inspection, 24/7 roadside assistance, a warranty of: 2 Years / Unlimited Miles Warranty coverage after the expiration of the new vehicle limited warranty or from the date of sale if the new vehicle limited warranty has expired. The new vehicle limited warranty coverage is 6 years / unlimited miles from the original in-service date., The new vehicle limited warranty covers up to 6 years / Unlimited Miles, if the vehicle is still under the new car limited warranty. ;2 Years / Unlimited Miles 24 hour Porsche Roadside Assistance ;No Deductible KEY FEATURES AND OPTIONS Comes equipped with: Air Conditioning, MP3, Bluetooth, Navigation System. This Porsche 911 also includes Clock, Climate Control, Tachometer, Multi-Zone Climate Control, Memory Seat Position, Cruise Control, Homelink System, Power Steering, Telescoping Steering Wheel, Power Windows, Steering Radio Controls, Power Mirrors, Driver Airbag, Passenger Airbag, Intermittent Wipers, Side Airbags, Keyless Entry, Rear Defogger, Security System, AM/FM, Rear Spoiler, Center Arm Rest, Cup Holders, Vanity Mirrors, Heated Mirrors, Rear Fog Lamps, Daytime Running Lights, Reverse Camera, Side Curtain Airbags, Roll Stability Control, PCM, Tire Pressure Monitor, Carpeted Floor Mats, Overhead Console, Aux. Audio Input, HD Radio, Voice Control. Air Conditioning, Climate Control, Dual Zone Climate Control, Cruise Control, Power Steering, Power Windows, Power Mirrors, Memory Seat Position, Clock, Tachometer, Homelink System, Telescoping Steering Wheel, Steering Wheel Radio Controls, Driver Airbag, Passenger Airbag, Side Airbags, Keyless Entry, Security System, Rear Defogger, Intermittent Wipers, AM/FM, Leather/Suede Interior Surface, Aux. Audio Input, Bluetooth, Carpeted Floor Mats, Center Arm Rest, Cup Holders, Daytime Running Lights, Heated Mirrors, Navigation System, Overhead Console, PCM, Rear Fog Lamps, Rear Spoiler, Rear Spoiler, Reverse Camera, Roll Stability Control, Side Curtain Airbags, Tire Pressure Monitor, Vanity Mirrors - Contact William Pena at *number in the description* or for more information.",
                    "https://d2blhp03rkvfi4.cloudfront.net/cache/width_916/car/car-287690/photo/2024-porsche-911--gt3--rs-510992-63342772.webp",
                    (long) 510992,
                    Condition.USED,
                    false,
                    Style.COUPE,
                    2024,
                    Fuel.PETROL,
                    (long) 55,
                    Transmission.AUTOMATIC,
                    DriveTrain.RWD,
                    520,
                    (float) 4,
                    6,
                    EngineType.F6,
                    Color.BLACK,
                    Color.BLUE,
                    2,
                    false,
                    LocalDateTime.now(),
                    LocalDateTime.now(),
                    pictures,
                    this.userRepository.findAll().getLast(),
                    carModel1
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


