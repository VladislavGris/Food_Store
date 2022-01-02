package by.grishkevich.food_store_data.services.data.base;

import by.grishkevich.food_store_data.models.Client;

public interface ClientService {
    boolean isEmailExists(String email);
    boolean checkUserCredentials(String login, String password);
    Client save(Client client);
    Client findByLoginAndPassword(String login, String password);
    Client findByEmail(String email);
    Iterable<Client> findAllClients();
    Client getById(Long id);
    Client update(Client client);
}
