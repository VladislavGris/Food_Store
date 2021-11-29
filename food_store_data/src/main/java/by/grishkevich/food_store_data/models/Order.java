package by.grishkevich.food_store_data.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Set;

@Data
@Entity
@Table(name = "Orders")
public class Order extends BaseEntity{

    @Column(name = "Date")
    private LocalDate date;

    @Column(name = "Time")
    private LocalTime time;

    @Column(name = "placed_at")
    private LocalDateTime placedAt;

    @Column(name = "State")
    @Enumerated(EnumType.STRING)
    private OrderState state = OrderState.Placed;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "Orders_Products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products;

    @PrePersist
    void placedAt(){
        this.placedAt = LocalDateTime.now();
    }
}
