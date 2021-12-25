package by.grishkevich.food_store_data.exceptions;

public class TrademarkNotFoundException extends RuntimeException{

    public TrademarkNotFoundException(Long id){
        super(String.format("Trademark with Id %d not found",id));
    }
}
