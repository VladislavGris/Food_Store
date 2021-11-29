package by.grishkevich.food_store_data.services;

import by.grishkevich.food_store_data.models.Administrator;
import by.grishkevich.food_store_data.models.Client;
import by.grishkevich.food_store_data.repositories.AdministratorRepository;
import by.grishkevich.food_store_data.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class PersonsReposUserDetailsService implements UserDetailsService {

    private ClientRepository clientRepo;
    private AdministratorRepository adminRepo;

    @Autowired
    public PersonsReposUserDetailsService(ClientRepository clientRepo, AdministratorRepository adminRepo){
        this.clientRepo = clientRepo;
        this.adminRepo = adminRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Administrator admin = adminRepo.findByEmail(email);
        if(admin == null){
            Client client = clientRepo.findByEmail(email);
            if(client != null){
                return client;
            }
            throw new UsernameNotFoundException("Email '" + email + "' not found");
        }
        return admin;
    }
}
