package by.grishkevich.food_store_data.models;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "Users")
public class Client extends Person{

    @Column(name = "Address")
    @NotBlank(message = "Address can not be empty")
    private String address;

    @Column(name = "Phone")
    @NotBlank(message = "Phone can not be empty")
    //@Pattern(regexp = "/\\(?([0-9]{3})\\)?([ .-]?)([0-9]{3})\\2([0-9]{4})/", message = "Phone number format is not correct")
    private String phone;
}
