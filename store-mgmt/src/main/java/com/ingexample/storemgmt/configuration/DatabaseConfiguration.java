package com.ingexample.storemgmt.configuration;

import com.ingexample.storemgmt.entity.Product;
import com.ingexample.storemgmt.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DatabaseConfiguration {
    
    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {

        return args -> {
            repository.save(new Product("Telefon", 500d, 42, LocalDate.of(2026, 10, 10)));
            repository.save(new Product("Casa", 20000d, 20, LocalDate.of(2056, 10, 10)));
        };
    }
}
