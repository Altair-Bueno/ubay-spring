package uma.taw.ubayspring.dto.users;


import lombok.Value;
import uma.taw.ubayspring.types.GenderEnum;

import java.sql.Date;

/**
 * @author José Luis Bueno Pachón
 */

@Value
public class ClientDTO {
    int id;
    String name;
    String lastName;
    GenderEnum gender;
    String address;
    String city;
    Date birthDate;
}