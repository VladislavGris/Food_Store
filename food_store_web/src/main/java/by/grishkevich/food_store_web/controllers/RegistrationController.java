package by.grishkevich.food_store_web.controllers;

import by.grishkevich.food_store_data.repositories.ClientRepository;
import by.grishkevich.food_store_data.services.data.implementation.ClientJPAService;
import by.grishkevich.food_store_web.forms.RegistrationForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/register")
public class RegistrationController {

    private ClientJPAService clientService;
    private PasswordEncoder passwordEncoder;

    public RegistrationController(ClientJPAService clientService, PasswordEncoder passwordEncoder){
        this.clientService = clientService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm(){
        log.info("Going to registration");
        return "login/registration";
    }

    @PostMapping
    public String processRegistration(Model model, @Valid RegistrationForm form, Errors errors){
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
