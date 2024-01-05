package com.magpie.magpiecodingchallenge.Config;

import com.magpie.magpiecodingchallenge.Model.Product;
import com.magpie.magpiecodingchallenge.Repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ProductConfig {
        @Bean
        CommandLineRunner commandLineRunner(ProductRepository productRepository){
        return args -> {
            Product test1 = Product.builder()
                    .name("Sample Pasasroduct")
                    .description("Sample Descasasription")
                    .type("Sample Typeasasa")
                    .quantity(10123L)
                    .price(25123L)
                    .build();

            productRepository.saveAll(
                    List.of(test1)
            );
        };
    }
}
