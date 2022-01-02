package by.grishkevich.food_store_data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private String name;
    private String surname;
    private String email;
    private String role;
    private boolean isActive;
    private String address;
    private String phone;
}
