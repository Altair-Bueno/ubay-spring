package uma.taw.ubayspring.dto.users;

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
public class FilterUsersDTO {
    Integer id;
    String name;
    String lastName;
    GenderEnum gender;
    String address;
    String city;
    Date birthDate;
}
