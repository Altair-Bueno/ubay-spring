package uma.taw.ubayspring.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uma.taw.ubayspring.types.GenderEnum;

import java.sql.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    String username;
    String password;
    String repeatPassword;
    String name;
    String lastName;
    String address;
    String city;
    GenderEnum gender;
    Date birthDate;
}
