package by.grishkevich.food_store_data.models;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Table(name = "Trademarks")
public class Trademark extends BaseEntity{

    @NotEmpty(message = "Название торговой марки является обязательным")
    @Column(name = "Name")
    private String name;
}
