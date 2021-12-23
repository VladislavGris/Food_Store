package by.grishkevich.food_store_data.services.data.base;

import by.grishkevich.food_store_data.models.Order;

public interface OrderService {
    Iterable<Order> getAllOrders();
    Order getById(Long id);
    Order update(Order updOrder, Long id);
    Order save(Order order);
    void delete(Long id);
}
