package by.grishkevich.food_store_web.controllers.rest;

import by.grishkevich.food_store_data.dto.ClientGetDTO;
import by.grishkevich.food_store_data.dto.ClientPostDTO;
import by.grishkevich.food_store_data.mappers.base.ClientMapper;
import by.grishkevich.food_store_data.models.Client;
import by.grishkevich.food_store_data.models.VerificationToken;
import by.grishkevich.food_store_data.services.data.implementation.ClientJPAService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/clients")
@ComponentScan({"by.grishkevich.food_store_data.mappers"})
@Slf4j
public class ClientsController {

    private ClientJPAService clientService;
    private ClientMapper clientMapper;

    public ClientsController(ClientJPAService clientService, ClientMapper clientMapper){
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @GetMapping
    public Iterable<ClientGetDTO> getUsers(){
        List<ClientGetDTO> clients = new ArrayList<>();
        for(Client client:clientService.findAllClients()){
            clients.add(clientMapper.clientToClientGetDto(client));
        }
        return clients;
    }

    @GetMapping("/activate/{id}")
    public void activateUser(@PathVariable Long id){
        clientService.activateUser(id);
        log.info("Пользователь с Id " + id + " активирован");
    }

    @GetMapping("/deactivate/{id}")
    public void deactivateUser(@PathVariable Long id){
        clientService.deactivateUser(id);
        log.info("Пользователь с Id " + id + " деактивирован");
    }

    @GetMapping("/activate")
    public ResponseEntity<Object> activateByToken(@RequestParam("token") String token){
        VerificationToken myToken = clientService.getToken(token);
        if(myToken == null){
            return new ResponseEntity<>("Invalid token", HttpStatus.NOT_FOUND);
        }
        Client client = myToken.getClient();
        Calendar cal = Calendar.getInstance();
        if ((myToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return new ResponseEntity<>("Verification token is expired", HttpStatus.UNAUTHORIZED);
        }
        clientService.activateUser(client.getId());
        return new ResponseEntity<>("Activation successful", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public Client getUser(@PathVariable Long id){
        return clientService.getById(id);
    }

    @PutMapping("/{id}")
    public Client updateUser(@PathVariable Long id, @Valid @RequestBody ClientPostDTO client){
        return clientService.update(clientMapper.clientPostDtoToClient(client), id);
    }

}
