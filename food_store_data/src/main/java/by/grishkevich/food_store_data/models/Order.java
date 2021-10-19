package by.grishkevich.food_store_data.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "Orders")
public class Order extends BaseEntity{

    @Column(name = "Date")
    @NotNull(message = "Date can not be empty")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @Column(name = "Time")
    @NotNull(message = "Time can not be empty")
    private LocalTime time;

    @Column(name = "PlacedAt")
    private Date placedAt;

    @Column(name = "State")
    @Enumerated(EnumType.STRING)
    private OrderState state = OrderState.Placed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ClientId")
    @NotNull
    private Client client;

    @ManyToMany
    @JoinTable(name = "Orders_Products",
            joinColumns = @JoinColumn(name = "OrderId"),
            inverseJoinColumns = @JoinColumn(name = "ProductId"))
    private Set<Product> products;

    @PrePersist
    void placedAt(){
        this.placedAt = new Date();
    }
}
