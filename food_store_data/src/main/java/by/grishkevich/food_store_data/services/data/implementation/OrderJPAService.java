package by.grishkevich.food_store_data.services.data.implementation;

import by.grishkevich.food_store_data.exceptions.OrderNotFoundException;
import by.grishkevich.food_store_data.exceptions.UserNotFoundException;
import by.grishkevich.food_store_data.models.Client;
import by.grishkevich.food_store_data.models.Order;
import by.grishkevich.food_store_data.models.Product;
import by.grishkevich.food_store_data.repositories.ClientRepository;
import by.grishkevich.food_store_data.repositories.OrderRepository;
import by.grishkevich.food_store_data.services.data.base.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Service
@Slf4j
public class OrderJPAService implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;

    public OrderJPAService(OrderRepository orderRepository, ClientRepository clientRepository){
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
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

    @Override
    public void processOrder(LocalDate date, LocalTime time, Long clientId, Set<Product> products) {
        Client client = clientRepository.findById(clientId).orElseThrow(()->new UserNotFoundException("Пользователь, оформляющий заказ, не найден"));
        Order order = new Order();
        order.setProducts(products);
        order.setTime(time);
        order.setClient(client);
        order.setDate(date);
        log.error(order.toString());
        orderRepository.save(order);
    }
}
