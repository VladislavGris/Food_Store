package by.grishkevich.food_store_web.controllers.rest;

import by.grishkevich.food_store_data.models.Client;
import by.grishkevich.food_store_data.services.data.base.ClientService;
import by.grishkevich.food_store_web.responses.AuthResponse;
import by.grishkevich.food_store_web.security.JwtProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:3000/")
public class AuthController {

    private ClientService clientService;
    private JwtProvider jwtProvider;

    public AuthController(ClientService clientService, JwtProvider jwtProvider){
        this.clientService = clientService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("register")
    public Client registerUser(@Valid @RequestBody Client client){
        log.info("AuthController::registerUser");
        return clientService.save(client);
    }

    @PostMapping("login")
    public AuthResponse authUser(@RequestBody Client client){
        log.info("AuthController::authUser");
        Client authClient = clientService.findByLoginAndPassword(client.getEmail(), client.getPassword());
        String token = jwtProvider.generateToken(authClient.getEmail());

        return new AuthResponse(token,authClient.getId(),authClient.getRole());
    }
}
