package by.grishkevich.food_store_web.requests;

import lombok.Data;

@Data
public class ProductRequest {
    String name;
    double price;
    String country;
    String category;
    String trademark;
    String imageRef;
}
