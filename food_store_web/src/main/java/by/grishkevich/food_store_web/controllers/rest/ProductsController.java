package by.grishkevich.food_store_web.controllers.rest;

import by.grishkevich.food_store_data.models.Product;
import by.grishkevich.food_store_data.services.data.implementation.ProductJPAService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/products")
public class ProductsController {

    private ProductJPAService productService;

    public ProductsController(ProductJPAService productService){
        this.productService = productService;
    }

    @GetMapping
    public Iterable<Product> getProducts(){
        return productService.getAllProducts();
    }

    @PostMapping
    public Product newProduct(@RequestBody Product newProduct){
        return productService.save(newProduct);
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id){
        return productService.getById(id);
    }

    @PutMapping("/{id}")
    public  Product updateProduct(@RequestBody Product updatedProduct, @PathVariable Long id){
        return productService.update(updatedProduct,id);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.delete(id);
    }
}