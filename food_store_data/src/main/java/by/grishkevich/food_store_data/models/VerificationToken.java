package by.grishkevich.food_store_data.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.yaml.snakeyaml.tokens.FlowEntryToken;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "verification_token")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VerificationToken extends BaseEntity{

    private static final int EXPIRATION = 60*24;

    private String token;

    @OneToOne(targetEntity = Client.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "expiry_date")
    private Date expiryDate;

    private Date calculateExpiryDate(int minutes){
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, minutes);
        return new Date(cal.getTime().getTime());
    }

    public VerificationToken(Client client, String token){
        this.client = client;
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRATION);
    }
}
