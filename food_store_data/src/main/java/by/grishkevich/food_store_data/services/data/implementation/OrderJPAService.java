package by.grishkevich.food_store_data.services.data.implementation;

import by.grishkevich.food_store_data.models.Order;
import by.grishkevich.food_store_data.repositories.OrderRepository;
import by.grishkevich.food_store_data.services.data.base.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

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
        try{
            return orderRepository.findById(id).get();
        }catch (NoSuchElementException ex){
            return null;
        }
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
                }).orElseGet(() -> {
                    updOrder.setId(id);
                    return orderRepository.save(updOrder);
                });
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
