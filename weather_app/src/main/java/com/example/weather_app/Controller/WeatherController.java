package com.example.weather_app.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WeatherController {

    @GetMapping("/")
    public String hello(){
        return "hello world";
    }
}
