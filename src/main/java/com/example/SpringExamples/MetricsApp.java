package com.example.SpringExamples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MetricsApp {
    public static void main(String[] args) {
        SpringApplication.run(MetricsApp.class, args);
    }
}

