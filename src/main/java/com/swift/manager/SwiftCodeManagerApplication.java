package com.swift.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SwiftCodeManagerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SwiftCodeManagerApplication.class, args);
    }
}