package by.grishkevich.food_store_data.services.data.implementation;

import by.grishkevich.food_store_data.models.Client;
import by.grishkevich.food_store_data.repositories.ClientRepository;
import by.grishkevich.food_store_data.services.data.base.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClientJPAService implements ClientService {
    private final ClientRepository clientRepo;

    public ClientJPAService(ClientRepository clientRepo){
        this.clientRepo = clientRepo;
    }

    @Override
    public Client findByEmail(String email) {
        return clientRepo.findByEmail(email);
    }

    @Override
    public boolean isEmailExists(String email) {
        if(clientRepo.findByEmail(email)!=null){
            log.warn("Client with email '" + email + "' already exists");
            return true;
        }
        log.info("Client with email '" + email + "' does not exists");
        return false;
    }

    @Override
    public void registerClient(Client client) {
        clientRepo.save(client);
        log.info("Client with email '" + client.getEmail() + "' registered");
    }
}
