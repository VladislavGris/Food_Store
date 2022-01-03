package by.grishkevich.food_store_data.repositories;

import by.grishkevich.food_store_data.models.Client;
import by.grishkevich.food_store_data.models.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    Iterable<Order> findAllByClient(Client client);
}
