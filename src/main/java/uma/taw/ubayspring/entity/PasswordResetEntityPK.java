package uma.taw.ubayspring.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

/**
 * @author Altair Bueno
 */

@Data
public class PasswordResetEntityPK implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "login_id", nullable = false)
    private LoginCredentialsEntity loginCredentials;
    @Id
    @Column(name = "request_id", nullable = false, length = 20)
    private String requestId;

    public PasswordResetEntityPK() {
    }

    public PasswordResetEntityPK(LoginCredentialsEntity loginCredentials, String requestId) {
        this.loginCredentials = loginCredentials;
        this.requestId = requestId;
    }
}
