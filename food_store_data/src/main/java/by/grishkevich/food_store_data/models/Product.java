package by.grishkevich.food_store_data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "Products")
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {

    @NotEmpty(message = "Название продукта является обязательным")
    @Column(name = "Name")
    private String name;

    @Range(min = 0, message = "Цена не должна быть отрицательной")
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
