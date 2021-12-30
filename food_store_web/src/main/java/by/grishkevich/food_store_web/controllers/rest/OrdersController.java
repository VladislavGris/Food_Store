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

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id){
        return orderService.getById(id);
    }

    @PostMapping
    public void newOrder(@RequestBody OrderRequest order){
        log.info(order.getDate().toString());
        log.info(order.getTime().toString());
        log.info(order.getClient().toString());
        log.info(order.getProducts().toString());
        Order order1 = orderService.processOrder(order.getDate(),order.getTime(),order.getClient(),order.getProducts());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("vlad.grishkevich0503@gmail.com");
        //order1.getClient().getEmail()
        message.setTo("vladislav.grishkevich@gmail.com");
        message.setSubject("Обработка заказа");
        message.setText("Ваш заказ с номером " + order1.getId() + " одобрен. Ожидайте доставки");
        emailSender.send(message);
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
