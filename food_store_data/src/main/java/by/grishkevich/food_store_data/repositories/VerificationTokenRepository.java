package by.grishkevich.food_store_data.repositories;

import by.grishkevich.food_store_data.models.Client;
import by.grishkevich.food_store_data.models.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    VerificationToken findByToken(String token);
    VerificationToken findByClient(Client client);
}
