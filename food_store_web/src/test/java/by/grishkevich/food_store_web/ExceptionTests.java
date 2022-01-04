package by.grishkevich.food_store_web;

import by.grishkevich.food_store_data.exceptions.CategoryNotFoundException;
import by.grishkevich.food_store_data.exceptions.CountryNotFoundException;
import by.grishkevich.food_store_data.exceptions.TrademarkNotFoundException;
import by.grishkevich.food_store_data.repositories.CategoryRepository;
import by.grishkevich.food_store_data.services.data.base.CategoryService;
import by.grishkevich.food_store_data.services.data.base.Countryservice;
import by.grishkevich.food_store_data.services.data.base.TrademarkService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ExceptionTests {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private Countryservice countryService;
    @Autowired
    private TrademarkService trademarkService;

    @Test
    public void categoryNotFoundTest(){
        Exception exception = Assertions.assertThrows(CategoryNotFoundException.class, ()->{
            categoryService.getById(12345l);
        });
        String expectedMessage = "Category with Id 12345 not found";
        String actualMessage = exception.getMessage();
        log.info("Expected message: " + expectedMessage);
        log.info("Actual message: " + actualMessage);
        Assertions.assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void countryNotFoundTest(){
        Exception exception = Assertions.assertThrows(CountryNotFoundException.class, ()->{
            countryService.getById(12345l);
        });
        String expectedMessage = "Country with Id 12345 not found";
        String actualMessage = exception.getMessage();
        log.info("Expected message: " + expectedMessage);
        log.info("Actual message: " + actualMessage);
        Assertions.assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void trademarkNotFoundTest(){
        Exception exception = Assertions.assertThrows(TrademarkNotFoundException.class, ()->{
            trademarkService.getById(12345l);
        });
        String expectedMessage = "Trademark with Id 12345 not found";
        String actualMessage = exception.getMessage();
        log.info("Expected message: " + expectedMessage);
        log.info("Actual message: " + actualMessage);
        Assertions.assertEquals(expectedMessage,actualMessage);
    }

}
