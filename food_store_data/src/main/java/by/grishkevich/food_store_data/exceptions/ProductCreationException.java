package by.grishkevich.food_store_data.exceptions;

public class ProductCreationException extends RuntimeException{
    public ProductCreationException(String msg){
        super(String.format("Trademark with Id %s not found",msg));
    }
}
