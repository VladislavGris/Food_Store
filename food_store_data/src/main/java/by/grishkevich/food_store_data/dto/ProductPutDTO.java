package by.grishkevich.food_store_data.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPutDTO {
    @NotEmpty(message = "Название продукта является обязательным")
    private String name;
    @Range(min = 0, message = "Цена не должна быть отрицательной")
    private double price;
    private String imageRef;
    @NotEmpty(message = "Торговая марка является обязательной")
    private String trademark;
    @NotEmpty(message = "Страна является обязательной")
    private String country;
    @NotEmpty(message = "Категория является обязательной")
    private String category;
}
