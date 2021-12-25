package by.grishkevich.food_store_data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Users")
public class Client extends Person{

    @NotEmpty(message = "Адрес является обязательным полем")
    @Column(name = "Address")
    private String address;

    @NotBlank(message = "Телефон является обязательным полем")
    @Column(name = "Phone")
    private String phone;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
    }

    public Client(String name, String surname, String email, String password, String address, String phone){
        super(name, surname, email, password);
        this.address = address;
        this.phone = phone;
    }
}
