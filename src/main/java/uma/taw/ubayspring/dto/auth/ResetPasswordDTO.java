package uma.taw.ubayspring.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResetPasswordDTO {
    private Integer loginID;
    private String requestID;
    private String newPassword;
    private String repeatPassword;
}
