package by.grishkevich.food_store_data.mappers.implementation;

import by.grishkevich.food_store_data.dto.ClientGetDTO;
import by.grishkevich.food_store_data.dto.ClientPostDTO;
import by.grishkevich.food_store_data.mappers.base.ClientMapper;
import by.grishkevich.food_store_data.models.Client;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ClientMapperImpl implements ClientMapper {
    @Override
    public ClientGetDTO clientToClientGetDto(Client client) {
        if(client == null){
            log.warn("Client is null");
            return null;
        }


        ClientGetDTO clientGetDTO = new ClientGetDTO();

        clientGetDTO.setActive(client.isActive());
        clientGetDTO.setAddress(client.getAddress());
        clientGetDTO.setEmail(client.getEmail());
        clientGetDTO.setRole(client.getRole());
        clientGetDTO.setId(client.getId());
        clientGetDTO.setName(client.getName());
        clientGetDTO.setSurname(client.getSurname());
        clientGetDTO.setPhone(client.getPhone());

        return clientGetDTO ;
    }

    @Override
    public Client clientPostDtoToClient(ClientPostDTO clientPostDTO) {
        if(clientPostDTO == null){
            log.warn("ClientPostDto is null");
            return null;
        }
        Client client = new Client();

        client.setName(clientPostDTO.getName());
        client.setSurname(clientPostDTO.getSurname());
        client.setEmail(clientPostDTO.getEmail());
        client.setPassword(clientPostDTO.getPassword());
        client.setAddress(clientPostDTO.getAddress());
        client.setPhone(clientPostDTO.getPhone());

        return client;
    }
}
