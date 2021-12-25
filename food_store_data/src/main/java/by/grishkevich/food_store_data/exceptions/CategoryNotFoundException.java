package by.grishkevich.food_store_data.exceptions;

public class CategoryNotFoundException extends RuntimeException{

    public CategoryNotFoundException(Long id){
        super(String.format("Category with Id %d not found",id));
    }
}
