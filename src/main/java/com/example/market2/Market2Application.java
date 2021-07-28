package com.example.market2;

import com.example.market2.entity.Product;
import com.example.market2.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableJpaRepositories("com.example.market2.repository")
public class Market2Application {

    public static void main(String[] args) {

        SpringApplication.run(Market2Application.class, args);
    }

//    @Bean
//    public CommandLineRunner run(ProductRepository productRepository) {
//        return (String[] args) -> {
//            Product biscuit = new Product();
//            biscuit.setDescription("A premeium range biscuit by Parle");
//            biscuit.setMrp(100);
//            biscuit.setSku_qty(10);
//            biscuit.setName("Hide And Seek");
//            Product inhaler = new Product();
//            inhaler.setDescription("Congestion Relief med");
//            inhaler.setMrp(45);
//            inhaler.setSku_qty(20);
//            inhaler.setName("Vicks Inhaler");
//            productRepository.save(biscuit);
//            productRepository.save(inhaler);
//        };
//    }
}
