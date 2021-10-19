package by.grishkevich.food_store_data.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Products")
public class Product extends BaseEntity {

    @Column(name = "Name")
    @NotBlank(message = "Product name is required")
    private String name;

    @Column(name = "Price")
    @NotNull
    @Min(value = 0L, message = "Price can not be negative")
    private double price;

    @Column(name = "Image_ref")
    private String imageRef;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TrademarkId")
    @NotNull
    private Trademark trademark;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CountryId")
    @NotNull
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoryId")
    @NotNull
    private Category category;

    @ManyToMany(mappedBy = "products")
    private Set<Order> orders = new HashSet<>();
}
