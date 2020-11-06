package com.example.vegetables;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
@MapperScan(basePackages = "com.example.vegetables.mapper")
public class VegetablesApplication {

    public static void main(String[] args) {
        SpringApplication.run(VegetablesApplication.class, args);
    }

}
