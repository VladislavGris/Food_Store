package by.grishkevich.food_store_data.exceptions;

public class UserAlreadyExistsException extends RuntimeException{

    public UserAlreadyExistsException(String email){
        super(String.format("User with login \"%s\" already exists",email));
    }
}
