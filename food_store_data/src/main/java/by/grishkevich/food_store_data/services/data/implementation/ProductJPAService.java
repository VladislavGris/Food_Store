package by.grishkevich.food_store_data.services.data.implementation;

import by.grishkevich.food_store_data.exceptions.ProductCreationException;
import by.grishkevich.food_store_data.exceptions.ProductNotFoundException;
import by.grishkevich.food_store_data.models.Category;
import by.grishkevich.food_store_data.models.Country;
import by.grishkevich.food_store_data.models.Product;
import by.grishkevich.food_store_data.models.Trademark;
import by.grishkevich.food_store_data.repositories.CategoryRepository;
import by.grishkevich.food_store_data.repositories.CountryRepository;
import by.grishkevich.food_store_data.repositories.ProductRepository;
import by.grishkevich.food_store_data.repositories.TrademarkRepository;
import by.grishkevich.food_store_data.services.data.base.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductJPAService implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final TrademarkRepository trademarkRepository;
    private final CountryRepository countryRepository;

    public ProductJPAService(ProductRepository productRepository, CategoryRepository categoryRepository, TrademarkRepository trademarkRepository, CountryRepository countryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.trademarkRepository = trademarkRepository;
        this.countryRepository = countryRepository;
    }

    @Override
    public Page<Product> getAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
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
    public Product save(String name, double price, String country, String category, String trademark, String imageRef) {
        Country country1 = countryRepository.findByName(country);
        Trademark trademark1 = trademarkRepository.findByName(trademark);
        Category category1 = categoryRepository.findByName(category);
        if(country1!=null && trademark1 != null && category1 != null){
            Product product = new Product(name, price, imageRef, trademark1, country1, category1);
            return productRepository.save(product);
        }
        throw new ProductCreationException("Ошибка при добавлении продукта");
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
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
                })
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
