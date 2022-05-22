package uma.taw.ubayspring.dto;

import lombok.Value;
import uma.taw.ubayspring.types.KindEnum;

/**
 * @author Altair Bueno
 */

@Value
public class LoginDTO {
    String username;
    KindEnum kind;
}