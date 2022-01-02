package by.grishkevich.food_store_web.controllers.rest;

import by.grishkevich.food_store_data.dto.ProductPutDTO;
import by.grishkevich.food_store_data.mappers.base.ProductMapper;
import by.grishkevich.food_store_data.models.Product;
import by.grishkevich.food_store_data.services.data.implementation.ProductJPAService;
import by.grishkevich.food_store_web.requests.ProductRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/products")
@ComponentScan({"by.grishkevich.food_store_data.mappers"})
public class ProductsController {

    private ProductJPAService productService;
    private ProductMapper productMapper;

    public ProductsController(ProductJPAService productService, ProductMapper productMapper){
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping
    public Page<Product> getProducts(Pageable pageable){
        return productService.getAllProducts(pageable);
    }

    @PostMapping
    public Product newProduct(@RequestBody ProductRequest newProduct){

        return productService.save(newProduct.getName(), newProduct.getPrice(), newProduct.getCountry(), newProduct.getCategory(), newProduct.getTrademark(), newProduct.getImageRef());
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable Long id){
        return productService.getById(id);
    }

    @PutMapping("/{id}")
    public  Product updateProduct(@Valid @RequestBody ProductPutDTO productPutDTO, @PathVariable Long id){
        log.info(productPutDTO.toString());
        return productService.update(productMapper.productPutDtoToProduct(productPutDTO),id);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.delete(id);
    }
}
