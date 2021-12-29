package by.grishkevich.food_store_data.repositories;

import by.grishkevich.food_store_data.models.Category;
import by.grishkevich.food_store_data.models.Country;
import by.grishkevich.food_store_data.models.Product;
import by.grishkevich.food_store_data.models.Trademark;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Iterable<Product> findAllByTrademark(Trademark trademark);
    Iterable<Product> findAllByCountry(Country country);
    Iterable<Product> findAllByCategory(Category category);
}
