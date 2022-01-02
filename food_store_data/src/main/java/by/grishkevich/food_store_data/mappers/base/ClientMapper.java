package by.grishkevich.food_store_data.mappers.base;

import by.grishkevich.food_store_data.dto.ClientGetDTO;
import by.grishkevich.food_store_data.dto.ClientPostDTO;
import by.grishkevich.food_store_data.models.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientGetDTO clientToClientGetDto(Client client);
    Client clientPostDtoToClient(ClientPostDTO clientPostDTO);
}
