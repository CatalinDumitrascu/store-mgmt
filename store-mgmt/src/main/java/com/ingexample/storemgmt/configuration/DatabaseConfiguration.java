package com.ingexample.storemgmt.configuration;

import com.ingexample.storemgmt.entity.Product;
import com.ingexample.storemgmt.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DatabaseConfiguration {

    private static final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {

        return args -> {
            log.info("Loading entity into db: " + repository.save(new Product("Telefon", 500d, 42, LocalDate.of(2026, 10, 10))));
            log.info("Loading entity into db: " + repository.save(new Product("Casa", 20000d, 20, LocalDate.of(2056, 10, 10))));
        };
    }
}
