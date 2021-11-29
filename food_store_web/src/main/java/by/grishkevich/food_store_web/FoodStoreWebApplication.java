package by.grishkevich.food_store_web;

import by.grishkevich.food_store_data.models.*;
import by.grishkevich.food_store_data.repositories.*;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("by.grishkevich.food_store_data.repositories")
@EntityScan("by.grishkevich.food_store_data.models")
public class FoodStoreWebApplication{

    private static final Logger log = LoggerFactory.getLogger(FoodStoreWebApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(FoodStoreWebApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(AdministratorRepository repository) {
        return (args) -> {
            log.info(repository.findByEmail("vlad.grishkevich0503@gmail.com").getName());
        };
    }
}
