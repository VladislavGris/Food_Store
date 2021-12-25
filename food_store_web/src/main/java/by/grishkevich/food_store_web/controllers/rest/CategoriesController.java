package by.grishkevich.food_store_web.controllers.rest;

import by.grishkevich.food_store_data.models.Category;
import by.grishkevich.food_store_data.services.data.implementation.CategoryJPAService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/categories")
public class CategoriesController {

    private CategoryJPAService categoryService;

    public CategoriesController(CategoryJPAService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public Iterable<Category> getCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getCategory(@PathVariable Long id){
        return  categoryService.getById(id);
    }

    @PostMapping
    public Category newCategory(@Valid @RequestBody Category category){
        return categoryService.save(category);
    }

    @PutMapping("/{id}")
    public Category getCategory(@Valid @RequestBody Category category, @PathVariable Long id){
        return categoryService.update(category,id);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id){
        categoryService.delete(id);
    }
}
