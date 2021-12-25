package by.grishkevich.food_store_data.models;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "Countries")
public class Country extends BaseEntity{

    @NotEmpty(message = "Название страны является обязательным")
    @Column(name = "Name")
    private String name;
}
