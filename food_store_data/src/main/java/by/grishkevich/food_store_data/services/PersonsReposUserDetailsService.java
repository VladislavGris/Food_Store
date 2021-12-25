package by.grishkevich.food_store_data.services;

import by.grishkevich.food_store_data.models.Client;
import by.grishkevich.food_store_data.models.Person;
import by.grishkevich.food_store_data.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PersonsReposUserDetailsService implements UserDetailsService {

    private ClientRepository clientRepo;

    @Autowired
    public PersonsReposUserDetailsService(ClientRepository clientRepo){
        this.clientRepo = clientRepo;
    }

    @Override
    public Person loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = clientRepo.findByEmail(email);
        if(client != null){
            throw new UsernameNotFoundException("Email '" + email + "' not found");
        }
        return client;
    }
}
