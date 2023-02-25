package com.weatherapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot application to create REST Endpoints to fetch weather details
 */
@SpringBootApplication
public class SpringBootWeatherApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWeatherApplication.class, args);
    }
}