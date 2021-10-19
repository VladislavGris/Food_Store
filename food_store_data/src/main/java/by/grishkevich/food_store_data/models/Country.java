package by.grishkevich.food_store_data.models;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "Countries")
public class Country extends BaseEntity{

    @Column(name = "Name")
    private String name;
}
