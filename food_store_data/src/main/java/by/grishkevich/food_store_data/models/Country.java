package by.grishkevich.food_store_data.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Countries")
public class Country extends BaseEntity{

    @Column(name = "Name")
    @NotBlank(message = "Country name can not be empty")
    private String name;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>();
}
