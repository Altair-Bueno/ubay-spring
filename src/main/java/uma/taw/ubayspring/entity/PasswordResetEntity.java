package uma.taw.ubayspring.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Altair Bueno
 */

@Entity
@Table(name = "password_reset", schema = "public", catalog = "UBAY")
@IdClass(PasswordResetEntityPK.class)
public class PasswordResetEntity {
    @Id
    @OneToOne
    @JoinColumn(name = "login_id", nullable = false)
    private LoginCredentialsEntity loginCredentials;
    @Id
    @Column(name = "request_id", nullable = false, length = 20)
    private String requestId;

    public LoginCredentialsEntity getLoginCredentials() {
        return loginCredentials;
    }

    public void setLoginCredentials(LoginCredentialsEntity loginCredentials) {
        this.loginCredentials = loginCredentials;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PasswordResetEntity)) return false;
        PasswordResetEntity that = (PasswordResetEntity) o;
        return Objects.equals(loginCredentials, that.loginCredentials) && Objects.equals(requestId, that.requestId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loginCredentials, requestId);
    }
}
