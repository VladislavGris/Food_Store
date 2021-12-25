package by.grishkevich.food_store_data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Collection;

@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
public class Person extends BaseEntity implements UserDetails {

    @NotEmpty(message = "Имя является обязательным")
    @Size(min = 3, max = 30, message = "Имя должно быть в диапазоне от 3 до 30 символов")
    @Column(name = "Name")
    private String name;

    @NotEmpty(message = "Фамилия является обязательной")
    @Size(min = 3, max = 30, message = "Фамилия должна быть в диапазоне от 3 до 30 символов")
    @Column(name = "Surname")
    private String surname;

    @NotEmpty(message = "Email является обязательным")
    @Email(message = "Email должен быть корректным")
    @Column(name = "Email")
    private String email;

    @NotEmpty(message = "Пароль является обязательным")
    @Size(min = 5, max = 70, message = "Пароль должен быть в диапазоне от 5 до 70 символов")
    @Column(name = "Password")
    private String password;

    public Person(Long id, String name, String surname, String email, String password){
        super(id);
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
