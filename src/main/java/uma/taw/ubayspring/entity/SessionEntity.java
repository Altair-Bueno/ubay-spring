package uma.taw.ubayspring.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author Altair Bueno
 */

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SessionEntity)) return false;
        SessionEntity that = (SessionEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(startDate, that.startDate) && Objects.equals(finishDate, that.finishDate) && Objects.equals(loginCredentials, that.loginCredentials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startDate, finishDate, loginCredentials);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(Timestamp finishDate) {
        this.finishDate = finishDate;
    }

    public LoginCredentialsEntity getLoginCredentials() {
        return loginCredentials;
    }

    public void setLoginCredentials(LoginCredentialsEntity loginCredentials) {
        this.loginCredentials = loginCredentials;
    }
}
