package by.grishkevich.food_store_data.services.data.implementation;

import by.grishkevich.food_store_data.exceptions.UserAlreadyExistsException;
import by.grishkevich.food_store_data.exceptions.UserNotFoundException;
import by.grishkevich.food_store_data.models.Client;
import by.grishkevich.food_store_data.repositories.ClientRepository;
import by.grishkevich.food_store_data.services.data.base.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClientJPAService implements ClientService {
    private final ClientRepository clientRepo;
    private final PasswordEncoder passwordEncoder;

    public ClientJPAService(ClientRepository clientRepo, PasswordEncoder passwordEncoder){
        this.clientRepo = clientRepo;
        this.passwordEncoder = passwordEncoder;
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
    public boolean checkUserCredentials(String login, String password) {
        return true;
    }

    @Override
    public Client save(Client client) {
        if(isEmailExists(client.getEmail()))
            throw new UserAlreadyExistsException(client.getEmail());
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        return clientRepo.save(client);
    }

    @Override
    public Client findByLoginAndPassword(String login, String password) {
        Client fundClient = findByEmail(login);
        if(fundClient == null)
            throw new UserNotFoundException("Пользователя с данным именем пользователя не существует");
        if(!passwordEncoder.matches(password, fundClient.getPassword()))
            throw new UserNotFoundException("Логин или пароль указаны неверно");
        return fundClient;
    }

    @Override
    public Client findByEmail(String email) {
        return clientRepo.findByEmail(email);
    }

    @Override
    public Iterable<Client> findAllClients() {
        return clientRepo.findAll();
    }

    @Override
    public Client getById(Long id) {
        return clientRepo.findById(id).orElseThrow(() -> new UserNotFoundException("Пользователь с ID " + id + " не найден"));
    }

    @Override
    public Client update(Client client, Long id) {

        return clientRepo.findById(id).map(user -> {
            user.setName(client.getName());
            user.setSurname(client.getSurname());
            user.setEmail(client.getEmail());
            user.setPassword(passwordEncoder.encode(client.getPassword()));
            user.setAddress(client.getAddress());
            user.setPhone(client.getPhone());
            user.setRole(client.getRole());
            user.setActive(client.isActive());
            return clientRepo.save(user);
        }).orElseThrow(()->new UserNotFoundException("Пользователь с Id " + id + " не найден"));
    }

    @Override
    public void activateUser(Long id) {
        clientRepo.findById(id).map(client -> {
            client.setActive(true);
            return clientRepo.save(client);
        }).orElseThrow(()-> new UserNotFoundException("Пользователь с Id " + id + " не найден"));
    }

    @Override
    public void deactivateUser(Long id) {
        clientRepo.findById(id).map(client -> {
            client.setActive(false);
            return clientRepo.save(client);
        }).orElseThrow(()-> new UserNotFoundException("Пользователь с Id " + id + " не найден"));
    }
}
