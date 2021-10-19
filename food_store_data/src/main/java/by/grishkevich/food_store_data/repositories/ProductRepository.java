package by.grishkevich.food_store_data.repositories;

import by.grishkevich.food_store_data.models.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
}
