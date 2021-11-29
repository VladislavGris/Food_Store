package by.grishkevich.food_store_data.models;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Arrays;
import java.util.Collection;

@Data
@Entity
@Table(name = "Administrators")
public class Administrator extends Person{

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

}
