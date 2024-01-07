package com.magpie.magpiecodingchallenge.Config;

import com.magpie.magpiecodingchallenge.Model.Product;
import com.magpie.magpiecodingchallenge.Repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

@Configuration
public class ProductConfig {
        @Bean
        CommandLineRunner commandLineRunner(ProductRepository productRepository){
        return args -> {
            List<Product> prod = productRepository.findAll();
            if(prod.isEmpty()){
                Product dummy1 = Product.builder()
                        .name("Refrigerator")
                        .description("A large container which is kept cool inside, usually by electricity, so that the food and drink in it stays fresh.")
                        .type("Appliance")
                        .quantity(10123L)
                        .price(25123L)
                        .build();

                Product dummy2 = Product.builder()
                        .name("TV")
                        .description("A telecommunication medium for transmitting moving images and sound.")
                        .type("Appliance")
                        .quantity(10123L)
                        .price(25123L)
                        .build();


                productRepository.saveAll(
                        List.of(dummy1,dummy2)
                );
            }

        };
    }
}
