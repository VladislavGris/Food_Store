package by.grishkevich.food_store_web.requests;

import by.grishkevich.food_store_data.models.Product;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class OrderRequest {
    private LocalDate date;
    private LocalTime time;
    private Long client;
    private Set<Product> products;
}
