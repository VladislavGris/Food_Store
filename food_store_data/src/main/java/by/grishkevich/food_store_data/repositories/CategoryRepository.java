package by.grishkevich.food_store_data.repositories;

import by.grishkevich.food_store_data.models.Category;
import org.springframework.data.repository.CrudRepository;

import javax.annotation.Resource;

@Resource
public interface CategoryRepository extends CrudRepository<Category, Long> {
    Category findByName(String name);
}
