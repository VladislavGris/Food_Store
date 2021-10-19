package by.grishkevich.food_store_data.models;

import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Administrators")
public class Administrator extends Person{

}
