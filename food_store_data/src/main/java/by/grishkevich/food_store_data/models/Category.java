package by.grishkevich.food_store_data.models;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "Categories")
public class Category extends BaseEntity{

    @NotEmpty(message = "Название категриии является обязательным")
    @Column(name = "Name")
    private String name;
}
