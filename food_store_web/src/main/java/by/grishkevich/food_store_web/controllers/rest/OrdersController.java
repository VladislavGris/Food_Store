package by.grishkevich.food_store_web.controllers.rest;

import by.grishkevich.food_store_data.models.Order;
import by.grishkevich.food_store_data.services.data.implementation.OrderJPAService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/orders")
public class OrdersController {

    private OrderJPAService orderService;

    public OrdersController(OrderJPAService orderService){
        this.orderService = orderService;
    }

    @GetMapping
    public Iterable<Order> getOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id){
        return orderService.getById(id);
    }

    @PostMapping
    public Order newOrder(@RequestBody Order order){
        return orderService.save(order);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@RequestBody Order order, @PathVariable Long id){
        return orderService.update(order,id);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id){
        orderService.delete(id);
    }

}
