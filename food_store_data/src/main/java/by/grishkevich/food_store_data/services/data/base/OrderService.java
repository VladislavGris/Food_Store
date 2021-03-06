package by.grishkevich.food_store_data.services.data.base;

import by.grishkevich.food_store_data.models.Order;
import by.grishkevich.food_store_data.models.Product;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public interface OrderService {
    Iterable<Order> getAllOrders();
    Order getById(Long id);
    Order update(Order updOrder, Long id);
    Order save(Order order);
    void delete(Long id);
    Order processOrder(LocalDate date, LocalTime time, Long clientId, Set<Product> products);
    void approveOrder(Long id);
    void completeOrder(Long id);
    Iterable<Order> getAllByUser(Long id);
}
