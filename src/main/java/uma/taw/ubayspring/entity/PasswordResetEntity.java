package uma.taw.ubayspring.entity;

import javax.persistence.*;

@Entity
@Table(name = "password_reset", schema = "public", catalog = "UBAY")
@IdClass(PasswordResetEntityPK.class)
public class PasswordResetEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "login_id", nullable = false)
    private Integer loginId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "request_id", nullable = false, length = 20)
    private String requestId;
    @ManyToOne
    @JoinColumn(name = "login_id", referencedColumnName = "id", nullable = false, insertable = false,updatable = false)
    private LoginCredentialsEntity loginCredentialsByLoginId;

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
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
        if (o == null || getClass() != o.getClass()) return false;

        PasswordResetEntity that = (PasswordResetEntity) o;

        if (loginId != null ? !loginId.equals(that.loginId) : that.loginId != null)
            return false;
        if (requestId != null ? !requestId.equals(that.requestId) : that.requestId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = loginId != null ? loginId.hashCode() : 0;
        result = 31 * result + (requestId != null ? requestId.hashCode() : 0);
        return result;
    }

    public LoginCredentialsEntity getLoginCredentialsByLoginId() {
        return loginCredentialsByLoginId;
    }

    public void setLoginCredentialsByLoginId(LoginCredentialsEntity loginCredentialsByLoginId) {
        this.loginCredentialsByLoginId = loginCredentialsByLoginId;
    }
}
