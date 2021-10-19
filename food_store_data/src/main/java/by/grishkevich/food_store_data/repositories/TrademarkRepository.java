package by.grishkevich.food_store_data.repositories;

import by.grishkevich.food_store_data.models.Trademark;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrademarkRepository extends CrudRepository<Trademark, Long> {
}
