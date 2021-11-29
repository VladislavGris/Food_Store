package by.grishkevich.food_store_web.forms;

import by.grishkevich.food_store_data.models.Client;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class RegistrationForm {
    @NotBlank(message = "Name can not be empty")
    private String name;
    @NotBlank(message = "Surname can not be empty")
    private String surname;
    @NotBlank(message = "Email is required")
    @Email(message = "Email format is not correct")
    private String email;
    @NotBlank(message = "Password is required")
    @Length(min = 4, max = 32, message = "Password length must be between 4 and 32 characters")
    private String password;
    @NotBlank(message = "Address can not be empty")
    private String address;
    @NotBlank(message = "Phone can not be empty")
    private String phone;

    public Client toClient(PasswordEncoder passwordEncoder){
        return new Client(name, surname, email, passwordEncoder.encode(password), address, phone);
    }
}
