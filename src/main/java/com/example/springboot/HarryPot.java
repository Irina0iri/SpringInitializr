package com.example.springboot;

import com.github.javafaker.Faker;
import com.github.javafaker.service.RandomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
@RestController
public class HarryPot {
    public RandomService randomService = new RandomService();
    Locale locale = new Locale("Ro");
    public Faker faker = new Faker(locale,randomService);
    @GetMapping("/character")
    public String getCharacter(){
        return faker.harryPotter().character();
    }



}
