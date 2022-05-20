package uma.taw.ubayspring.entity;

import lombok.Data;
import uma.taw.ubayspring.types.KindEnum;

import javax.persistence.*;

@Data
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
}
