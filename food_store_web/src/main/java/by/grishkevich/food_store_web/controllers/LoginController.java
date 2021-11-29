package by.grishkevich.food_store_web.controllers;

import by.grishkevich.food_store_web.forms.RegistrationForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping
    public String loginForm(){
        log.info("Going to login");
        return "login/login";
    }

    @PostMapping
    public String processRegistration(@RequestParam("login") String login, @RequestParam("password") String password){
        System.out.println(login + " " + password);
        return "login/login";
    }
}
