package uma.taw.ubayspring.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "session", schema = "public", catalog = "UBAY")
@IdClass(SessionEntityPK.class)
public class SessionEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "start_date", nullable = false)
    private Timestamp startDate;
    @Basic
    @Column(name = "finish_date", nullable = true)
    private Timestamp finishDate;
    @Id
    @ManyToOne
    @JoinColumn(name = "login_id", nullable = false)
    private LoginCredentialsEntity loginCredentials;

    public SessionEntity() {
    }

    public SessionEntity(Timestamp startDate, Timestamp finishDate, LoginCredentialsEntity loginCredentials) {
        this.startDate = startDate;
        this.finishDate = finishDate;
        this.loginCredentials = loginCredentials;
    }
}
