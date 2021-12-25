package by.grishkevich.food_store_data.exceptions;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(Long id){
        super(String.format("Product with Id %d not found",id));
    }
}
