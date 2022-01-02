package by.grishkevich.food_store_data.dto;

import by.grishkevich.food_store_data.validators.annotations.PhoneNumberConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientPostDTO {
    @NotEmpty(message = "Имя является обязательным")
    @Size(min = 3, max = 30, message = "Имя должно быть в диапазоне от 3 до 30 символов")
    private String name;
    @NotEmpty(message = "Фамилия является обязательной")
    @Size(min = 3, max = 30, message = "Фамилия должна быть в диапазоне от 3 до 30 символов")
    private String surname;
    @NotEmpty(message = "Email является обязательным")
    @Email(message = "Email должен быть корректным")
    private String email;
    @NotEmpty(message = "Пароль является обязательным")
    @Size(min = 5, max = 70, message = "Пароль должен быть в диапазоне от 5 до 70 символов")
    private String password;
    @NotEmpty(message = "Адрес является обязательным полем")
    private String address;
    @PhoneNumberConstraint
    @NotBlank(message = "Телефон является обязательным полем")
    private String phone;
}
