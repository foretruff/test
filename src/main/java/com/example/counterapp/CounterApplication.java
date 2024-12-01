package com.example.counterapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CounterApplication {

    public static void main(String[] args) {
        SpringApplication.run(com.example.counterapp.CounterApplication.class, args);
    }

}
