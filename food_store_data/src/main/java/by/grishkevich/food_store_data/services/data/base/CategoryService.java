package by.grishkevich.food_store_data.services.data.base;

import by.grishkevich.food_store_data.models.Category;

public interface CategoryService {
    Iterable<Category> getAllCategories();
    Category getById(Long id);
    Category update(Category updCategory, Long id);
    Category save(Category category);
    void delete(Long id);
}
