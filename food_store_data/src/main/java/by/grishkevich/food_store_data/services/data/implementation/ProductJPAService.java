package by.grishkevich.food_store_data.services.data.implementation;

import by.grishkevich.food_store_data.models.Category;
import by.grishkevich.food_store_data.models.Country;
import by.grishkevich.food_store_data.models.Product;
import by.grishkevich.food_store_data.models.Trademark;
import by.grishkevich.food_store_data.repositories.ProductRepository;
import by.grishkevich.food_store_data.services.data.base.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class ProductJPAService implements ProductService {

    private final ProductRepository productRepository;

    public ProductJPAService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    @Override
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Iterable<Product> getProductsByTrademark(Trademark trademark) {
        return productRepository.findAllByTrademark(trademark);
    }

    @Override
    public Iterable<Product> getProductsByCountry(Country country) {
        return productRepository.findAllByCountry(country);
    }

    @Override
    public Iterable<Product> getProductsByCategory(Category category) {
        return productRepository.findAllByCategory(category);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(Long id) {
        try{
            return productRepository.findById(id).get();
        }catch (NoSuchElementException ex){
            return null;
        }

    }

    @Override
    public Product update(Product updProduct, Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setCategory(updProduct.getCategory());
                    product.setCountry(updProduct.getCountry());
                    product.setPrice(updProduct.getPrice());
                    product.setImageRef(updProduct.getImageRef());
                    product.setName(updProduct.getName());
                    product.setTrademark(updProduct.getTrademark());
                    return product;
                }).orElseGet(() -> {
                    updProduct.setId(id);
                    return save(updProduct);
                });
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
