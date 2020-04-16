package org.example.springboot;

import org.example.springboot.r6api.API;
import org.example.springboot.r6api.AuthToken;
import org.example.springboot.r6api.UbiAuthApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
