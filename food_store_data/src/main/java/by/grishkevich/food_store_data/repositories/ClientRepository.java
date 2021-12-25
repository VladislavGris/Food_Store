package by.grishkevich.food_store_data.repositories;

import by.grishkevich.food_store_data.models.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    Client findByEmail(String email);
    Client findByEmailAndPassword(String email, String password);
}
