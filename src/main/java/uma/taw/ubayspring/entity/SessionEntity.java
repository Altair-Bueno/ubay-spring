package uma.taw.ubayspring.entity;

import javax.persistence.*;
import java.sql.Timestamp;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "login_id", nullable = false)
    private Integer loginId;
    @ManyToOne
    @JoinColumn(name = "login_id", referencedColumnName = "id", nullable = false, insertable = false,updatable = false)
    private LoginCredentialsEntity loginCredentialsByLoginId;

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

    public Integer getLoginId() {
        return loginId;
    }

    public void setLoginId(Integer loginId) {
        this.loginId = loginId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SessionEntity that = (SessionEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null)
            return false;
        if (finishDate != null ? !finishDate.equals(that.finishDate) : that.finishDate != null)
            return false;
        if (loginId != null ? !loginId.equals(that.loginId) : that.loginId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (finishDate != null ? finishDate.hashCode() : 0);
        result = 31 * result + (loginId != null ? loginId.hashCode() : 0);
        return result;
    }

    public LoginCredentialsEntity getLoginCredentialsByLoginId() {
        return loginCredentialsByLoginId;
    }

    public void setLoginCredentialsByLoginId(LoginCredentialsEntity loginCredentialsByLoginId) {
        this.loginCredentialsByLoginId = loginCredentialsByLoginId;
    }
}
