package by.grishkevich.food_store_web.responses;

import by.grishkevich.food_store_data.models.Client;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private String token;
    private Long clientId;
    private String clientRole;
}
