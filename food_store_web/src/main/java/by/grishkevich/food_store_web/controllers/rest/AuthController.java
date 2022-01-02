package by.grishkevich.food_store_web.controllers.rest;

import by.grishkevich.food_store_data.dto.ClientPostDTO;
import by.grishkevich.food_store_data.mappers.base.ClientMapper;
import by.grishkevich.food_store_data.models.Client;
import by.grishkevich.food_store_data.services.data.base.ClientService;
import by.grishkevich.food_store_web.events.OnRegistrationCompleteEvent;
import by.grishkevich.food_store_web.responses.AuthResponse;
import by.grishkevich.food_store_web.security.JwtProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:3000/")
public class AuthController {

    private ApplicationEventPublisher eventPublisher;
    private ClientService clientService;
    private JwtProvider jwtProvider;
    private ClientMapper clientMapper;

    public AuthController(ClientService clientService, JwtProvider jwtProvider, ClientMapper clientMapper, ApplicationEventPublisher eventPublisher){
        this.clientService = clientService;
        this.jwtProvider = jwtProvider;
        this.clientMapper = clientMapper;
        this.eventPublisher = eventPublisher;
    }

    @PostMapping("register")
    public Client registerUser(@Valid @RequestBody ClientPostDTO client){
        Client regClient = clientService.save(clientMapper.clientPostDtoToClient(client));
        eventPublisher.publishEvent(new OnRegistrationCompleteEvent(regClient));
        return regClient;
    }

    @PostMapping("login")
    public AuthResponse authUser(@RequestBody Client client){
        Client authClient = clientService.findByLoginAndPassword(client.getEmail(), client.getPassword());
        String token = jwtProvider.generateToken(authClient.getEmail());

        return new AuthResponse(token,authClient.getId(),authClient.getRole());
    }
}
