package com.management.store.db;

import com.management.store.entity.ProductEntity;
import com.management.store.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseLoader {
    private static final Logger log = LoggerFactory.getLogger(DatabaseLoader.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {

        return args -> {
            log.info("Init startup data" + repository.save(new ProductEntity("Bread", 1.5,10)));
            log.info("Init startup data " + repository.save(new ProductEntity("Cheese", 6,11)));
            log.info("Init startup data " + repository.save(new ProductEntity("Butter", 3,15)));
        };
    }
}
