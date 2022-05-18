package uma.taw.ubayspring.entity;

import lombok.Data;

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
    @Column(name = "kind", nullable = false, length = 10)
    private String kind;
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = true)
    private ClientEntity client;
}
