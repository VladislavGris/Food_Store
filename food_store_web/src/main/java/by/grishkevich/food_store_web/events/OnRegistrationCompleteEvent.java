package by.grishkevich.food_store_web.events;

import by.grishkevich.food_store_data.models.Client;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Setter
@Getter
public class OnRegistrationCompleteEvent extends ApplicationEvent {

    private Client client;

    public OnRegistrationCompleteEvent(Client client){
        super(client);
        this.client = client;
    }

}
