package uma.taw.ubayspring.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Altair Bueno
 */

@Data
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
}
