package by.grishkevich.food_store_web.controllers.rest;

import by.grishkevich.food_store_data.models.Order;
import by.grishkevich.food_store_data.services.data.implementation.OrderJPAService;
import by.grishkevich.food_store_web.requests.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/orders")
public class OrdersController {

    private OrderJPAService orderService;
    private JavaMailSender emailSender;
    public OrdersController(OrderJPAService orderService, JavaMailSender mailSender){
        this.orderService = orderService;
        this.emailSender = mailSender;
    }

    @GetMapping
    public Iterable<Order> getOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/client/{id}")
    public Iterable<Order> getOrdersByClient(@PathVariable Long id){
        return orderService.getAllByUser(id);
    }

    @GetMapping("/approve/{id}")
    public void approveOrder(@PathVariable Long id){
        orderService.approveOrder(id);
    }

    @GetMapping("/complete/{id}")
    public void completeOrder(@PathVariable Long id){
        orderService.completeOrder(id);
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id){
        return orderService.getById(id);
    }

    @PostMapping
    public void newOrder(@RequestBody OrderRequest order){
        Order order1 = orderService.processOrder(order.getDate(),order.getTime(),order.getClient(),order.getProducts());
    }

    @PutMapping("/{id}")
    public Order updateOrder(@Valid @RequestBody Order order, @PathVariable Long id){
        return orderService.update(order,id);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id){
        orderService.delete(id);
    }

}
