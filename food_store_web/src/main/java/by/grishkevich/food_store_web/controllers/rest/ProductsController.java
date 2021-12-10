package by.grishkevich.food_store_web.controllers.rest;

import by.grishkevich.food_store_data.models.Product;
import by.grishkevich.food_store_data.services.data.implementation.ProductJPAService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/")
public class ProductsController {

    private ProductJPAService productService;

    public ProductsController(ProductJPAService productService){
        this.productService = productService;
    }

    @GetMapping("products")
    public Iterable<Product> getProducts(){
        return productService.getAllProducts();
    }
}
