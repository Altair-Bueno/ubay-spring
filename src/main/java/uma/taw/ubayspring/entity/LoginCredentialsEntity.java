package uma.taw.ubayspring.entity;

import uma.taw.ubayspring.types.KindEnum;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Altair Bueno
 */

@Entity
@Table(name = "login_credentials", schema = "public", catalog = "UBAY")
public class LoginCredentialsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "username", nullable = false, length = 20)
    private String username;
    @Basic
    @Column(name = "password", nullable = false, length = 60)
    private String password;
    @Basic
    @Enumerated(EnumType.STRING)
    @Column(name = "kind", nullable = false, length = 10)
    private KindEnum kind;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = true)
    private ClientEntity client;

    public LoginCredentialsEntity() {
    }

    public LoginCredentialsEntity(String username, String password, KindEnum kind, ClientEntity client) {
        this.username = username;
        this.password = password;
        this.kind = kind;
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LoginCredentialsEntity)) return false;
        LoginCredentialsEntity that = (LoginCredentialsEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && kind == that.kind && Objects.equals(client, that.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, kind, client);
    }

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

    public KindEnum getKind() {
        return kind;
    }

    public void setKind(KindEnum kind) {
        this.kind = kind;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }
}
