package uma.taw.ubayspring.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class PasswordResetEntityPK implements Serializable {
    @Id
    @Column(name = "login_id", nullable = false)
    private Integer loginCredentials;
    @Id
    @Column(name = "request_id", nullable = false, length = 20)
    private String requestId;

}
