package com.example.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MarketApplication {

    @Autowired
    greetingController greetingController;
    public static void main(String[] args) {

        SpringApplication.run(MarketApplication.class, args);
    }

}
