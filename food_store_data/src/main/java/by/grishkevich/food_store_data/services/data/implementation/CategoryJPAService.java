package by.grishkevich.food_store_data.services.data.implementation;

import by.grishkevich.food_store_data.models.Category;
import by.grishkevich.food_store_data.repositories.CategoryRepository;
import by.grishkevich.food_store_data.services.data.base.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Slf4j
public class CategoryJPAService implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryJPAService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getById(Long id) {
        try{
            return categoryRepository.findById(id).get();
        }catch (NoSuchElementException ex){
            return null;
        }

    }

    @Override
    public Category update(Category updCategory, Long id) {
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setName(updCategory.getName());
                    return category;
                }).orElseGet(() -> {
                    updCategory.setId(id);
                    return save(updCategory);
                });
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}
