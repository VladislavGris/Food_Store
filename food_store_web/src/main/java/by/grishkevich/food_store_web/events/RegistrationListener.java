package by.grishkevich.food_store_web.events;

import by.grishkevich.food_store_data.models.Client;
import by.grishkevich.food_store_data.services.data.base.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    private ClientService clientService;
    private JavaMailSender mailSender;

    public RegistrationListener(ClientService clientService, JavaMailSender mailSender){
        this.clientService = clientService;
        this.mailSender = mailSender;
    }

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }


    private void confirmRegistration(OnRegistrationCompleteEvent event){
        Client client = event.getClient();
        String token = UUID.randomUUID().toString();
        clientService.createVerificationToken(client,token);

        log.info("Sending message to client");

        String recipientAddress = client.getEmail();
        String subject = "Подтверждение регистрации Food Store";
        String message = "Для продолжения регистрации на Food Store перейдите по ссылке, прикрепленной ниже," +
                " и активируйте учетную запись.";
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + "\r\n" + "http://localhost:8080/api/clients/activate?token="+token);
        mailSender.send(email);
    }
}
