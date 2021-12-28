package by.grishkevich.food_store_data.services;

import by.grishkevich.food_store_data.models.Client;
import by.grishkevich.food_store_data.models.Person;
import by.grishkevich.food_store_data.repositories.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PersonsReposUserDetailsService implements UserDetailsService {

    private ClientRepository clientRepo;

    @Autowired
    public PersonsReposUserDetailsService(ClientRepository clientRepo){
        this.clientRepo = clientRepo;
    }

    @Override
    public Person loadUserByUsername(String email) throws UsernameNotFoundException {
        log.error("Email " + email);
        Client client = clientRepo.findByEmail(email);
        log.error(client.toString());
        if(client == null){
            throw new UsernameNotFoundException("Email '" + email + "' not found");
        }
        return client;
    }
}
