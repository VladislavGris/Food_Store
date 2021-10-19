package by.grishkevich.food_store_data.models;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "Products")
public class Product extends BaseEntity {

    @Column(name = "Name")
    private String name;

    @Column(name = "Price")
    private double price;

    @Column(name = "Image_ref")
    private String imageRef;

    @ManyToOne
    @JoinColumn(name = "trademark_id")
    private Trademark trademark;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
