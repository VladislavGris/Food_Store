package by.grishkevich.food_store_data.services.data.base;

import by.grishkevich.food_store_data.models.Client;

public interface ClientService {
    Client findByEmail(String email);
    boolean isEmailExists(String email);
    void registerClient(Client client);
    boolean checkUserCredentials(String login, String password);
}
