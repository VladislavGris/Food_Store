package by.grishkevich.food_store_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("by.grishkevich.food_store_data.repositories")
@EntityScan("by.grishkevich.food_store_data.models")
public class FoodStoreWebApplication{

    public static void main(String[] args) {
        SpringApplication.run(FoodStoreWebApplication.class, args);
    }
}
