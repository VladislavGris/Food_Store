package by.grishkevich.food_store_data.services.data.base;

import by.grishkevich.food_store_data.models.Category;
import by.grishkevich.food_store_data.models.Country;
import by.grishkevich.food_store_data.models.Product;
import by.grishkevich.food_store_data.models.Trademark;

import java.util.Optional;

public interface ProductService {
    Iterable<Product> getAllProducts();
    Iterable<Product> getProductsByTrademark(Trademark trademark);
    Iterable<Product> getProductsByCountry(Country country);
    Iterable<Product> getProductsByCategory(Category category);
    Product save(Product product);
    Product save(String name, double price, String country, String category, String trademark, String imageRef);
    Product getById(Long id);
    Product update(Product product, Long id);
    void delete(Long id);
}
