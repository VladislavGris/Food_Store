package by.grishkevich.food_store_web.controllers;

import by.grishkevich.food_store_data.models.Client;
import by.grishkevich.food_store_data.services.data.implementation.ClientJPAService;
import by.grishkevich.food_store_web.forms.RegistrationForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

    private ClientJPAService clientService;
    private PasswordEncoder passwordEncoder;

    public LoginController(ClientJPAService clientService, PasswordEncoder passwordEncoder){
        this.clientService = clientService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String loginForm(Model model){
        log.info("Going to login");
        model.addAttribute("client", new Client());
        return "login/login";
    }

    @PostMapping
    public String processRegistration(@ModelAttribute("client") Client client){
        if(clientService.checkUserCredentials(client.getEmail(), passwordEncoder.encode(client.getPassword()))){
            return "/store/store";
        }
        return "login/login";
    }
}
