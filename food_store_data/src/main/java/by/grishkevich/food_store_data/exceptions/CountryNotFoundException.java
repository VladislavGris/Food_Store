package by.grishkevich.food_store_data.exceptions;

public class CountryNotFoundException extends RuntimeException{

    public CountryNotFoundException(Long id){
        super(String.format("Country with Id %d not found",id));
    }
}
