package by.grishkevich.food_store_data.services.data.implementation;

import by.grishkevich.food_store_data.exceptions.OrderNotFoundException;
import by.grishkevich.food_store_data.models.Order;
import by.grishkevich.food_store_data.repositories.OrderRepository;
import by.grishkevich.food_store_data.services.data.base.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderJPAService implements OrderService {

    private final OrderRepository orderRepository;

    public OrderJPAService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Override
    public Iterable<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Override
    public Order update(Order updOrder, Long id) {
        return orderRepository.findById(id)
                .map(order -> {
                    order.setClient(updOrder.getClient());
                    order.setDate(updOrder.getDate());
                    order.setTime(updOrder.getTime());
                    order.setPlacedAt(updOrder.getPlacedAt());
                    order.setProducts(updOrder.getProducts());
                    order.setState(updOrder.getState());
                    return order;
                })
                .orElseThrow(() -> new OrderNotFoundException(id));
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void delete(Long id) {
        orderRepository.deleteById(id);
    }
}
