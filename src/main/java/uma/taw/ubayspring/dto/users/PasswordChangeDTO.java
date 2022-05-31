package uma.taw.ubayspring.dto.users;

import lombok.Value;

/**
 * @author José Luis Bueno Pachón
 */

@Value
public class PasswordChangeDTO {
    Integer loginID;
    String requestID;
}
