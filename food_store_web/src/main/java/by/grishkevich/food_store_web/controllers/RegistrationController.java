package by.grishkevich.food_store_web.controllers;

import by.grishkevich.food_store_data.repositories.ClientRepository;
import by.grishkevich.food_store_data.services.data.implementation.ClientJPAService;
import by.grishkevich.food_store_data.models.Client;
import by.grishkevich.food_store_web.forms.RegistrationForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/register")
public class RegistrationController {

    private ClientJPAService clientService;
    private PasswordEncoder passwordEncoder;
    private Client client;

    public RegistrationController(ClientJPAService clientService, PasswordEncoder passwordEncoder){
        this.clientService = clientService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm(Model model){
        log.info("Going to registration");
        model.addAttribute("client", new Client());
        return "login/registration";
    }

    @PostMapping
    public String processRegistration(Model model, @ModelAttribute("client") @Valid Client client, BindingResult bindingResult, @Valid RegistrationForm form, Errors errors){
        System.out.println(client.toString());
        if(errors.hasErrors()){
            log.warn("Validation error");
            model.addAttribute("error","All fields are required");
            return "login/registration";
        }
        if(clientService.isEmailExists(form.getEmail())){
            return "login/registration";
        }
        clientService.registerClient(form.toClient(passwordEncoder));
        return "redirect:/login";
    }
}
