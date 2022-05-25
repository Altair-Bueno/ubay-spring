package uma.taw.ubayspring.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ChangePasswordDTO {
    private String oldPassword;
    private String password;
    private String repeatPassword;
}
