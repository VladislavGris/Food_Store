package by.grishkevich.food_store_data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public class Person extends BaseEntity{
    @Column(name = "Name")
    @NotBlank(message = "Name can not be empty")
    private String name;

    @Column(name = "Surname")
    @NotBlank(message = "Surname can not be empty")
    private String surname;

    @Column(name = "Email")
    @NotBlank(message = "Email is required")
    @Email(message = "Email format is not correct")
    private String email;

    @Column(name = "Password")
    @NotBlank(message = "Password is required")
    @Length(min = 4, max = 32, message = "Password length must be between 4 and 32 characters")
    private String password;

    public Person(Long id, String name, String surname, String email, String password){
        super(id);
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }
}
