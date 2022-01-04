package by.grishkevich.food_store_web;

import by.grishkevich.food_store_data.models.Category;
import by.grishkevich.food_store_data.models.Client;
import by.grishkevich.food_store_data.models.Product;
import by.grishkevich.food_store_data.repositories.ClientRepository;
import by.grishkevich.food_store_data.services.data.base.CategoryService;
import by.grishkevich.food_store_data.services.data.base.ClientService;
import by.grishkevich.food_store_data.services.data.base.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DataTests {

    @Autowired
    private ProductService productService;
    @Autowired
    private ClientService clientService;
    @Autowired
    private CategoryService categoryService;

    @Test
    public void productDataTest(){
        Long expectedId = 1l;
        Product product = productService.getById(expectedId);

        Assertions.assertNotNull(product);
        log.info("Product not null");
        Assertions.assertNotNull(product.getId());
        log.info("Product id not null");
        Long actualId = product.getId();
        log.info("Expected id: " + expectedId);
        log.info("Actual id: " + actualId);
        Assertions.assertEquals(expectedId,actualId);
    }

    @Test
    public void clientDataTest(){
        String email = "admin@admin.admin";
        Client client = clientService.findByEmail(email);

        Assertions.assertNotNull(client);
        log.info("Client not null");
        Assertions.assertNotNull(client.getEmail());
        log.info("Client email not null");
        String actualEmail = client.getEmail();
        log.info("Expected email: " + email);
        log.info("Actual email: " + actualEmail);
        Assertions.assertEquals(email,actualEmail);
    }

    @Test
    public void categoryDataTest(){
        Long expectedId = 1l;
        Category category = categoryService.getById(expectedId);

        Assertions.assertNotNull(category);
        log.info("Category not null");
        Assertions.assertNotNull(category.getId());
        log.info("Category id not null");
        Long actualId = category.getId();
        log.info("Expected id: " + expectedId);
        log.info("Actual id: " + actualId);
        Assertions.assertEquals(expectedId,actualId);
    }

}
