package uma.taw.ubayspring.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Altair Bueno
 */

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PasswordResetEntityPK)) return false;
        PasswordResetEntityPK that = (PasswordResetEntityPK) o;
        return Objects.equals(loginCredentials, that.loginCredentials) && Objects.equals(requestId, that.requestId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loginCredentials, requestId);
    }

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
}
