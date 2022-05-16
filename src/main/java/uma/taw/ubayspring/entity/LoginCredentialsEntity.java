package uma.taw.ubayspring.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author Altair Bueno
 */

@Entity
@Table(name = "login_credentials", schema = "public", catalog = "UBAY")
public class LoginCredentialsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "username", nullable = false, length = 20)
    private String username;
    @Basic
    @Column(name = "password", nullable = false, length = 60)
    private String password;
    @Basic
    @Column(name = "kind", nullable = false, length = 10)
    private String kind;
    @Basic
    @Column(name = "client_id", nullable = true)
    private Integer clientId;
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ClientEntity clientByClientId;
    @OneToMany(mappedBy = "loginCredentialsByLoginId")
    private Collection<PasswordResetEntity> passwordResetsById;
    @OneToMany(mappedBy = "loginCredentialsByLoginId")
    private Collection<SessionEntity> sessionsById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoginCredentialsEntity that = (LoginCredentialsEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null)
            return false;
        if (password != null ? !password.equals(that.password) : that.password != null)
            return false;
        if (kind != null ? !kind.equals(that.kind) : that.kind != null)
            return false;
        return clientId != null ? clientId.equals(that.clientId) : that.clientId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (kind != null ? kind.hashCode() : 0);
        result = 31 * result + (clientId != null ? clientId.hashCode() : 0);
        return result;
    }

    public ClientEntity getClientByClientId() {
        return clientByClientId;
    }

    public void setClientByClientId(ClientEntity clientByClientId) {
        this.clientByClientId = clientByClientId;
    }

    public Collection<PasswordResetEntity> getPasswordResetsById() {
        return passwordResetsById;
    }

    public void setPasswordResetsById(Collection<PasswordResetEntity> passwordResetsById) {
        this.passwordResetsById = passwordResetsById;
    }

    public Collection<SessionEntity> getSessionsById() {
        return sessionsById;
    }

    public void setSessionsById(Collection<SessionEntity> sessionsById) {
        this.sessionsById = sessionsById;
    }
}
