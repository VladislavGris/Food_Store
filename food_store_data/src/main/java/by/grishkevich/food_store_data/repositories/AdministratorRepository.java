package by.grishkevich.food_store_data.repositories;

import by.grishkevich.food_store_data.models.Administrator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministratorRepository extends CrudRepository<Administrator, Long> {
}
