package uma.taw.ubayspring.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Altair Bueno
 */

public class SessionEntityPK implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Id
    @Column(name = "login_id", nullable = false)
    private Integer loginCredentials;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SessionEntityPK)) return false;
        SessionEntityPK that = (SessionEntityPK) o;
        return Objects.equals(id, that.id) && Objects.equals(loginCredentials, that.loginCredentials);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loginCredentials);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLoginCredentials() {
        return loginCredentials;
    }

    public void setLoginCredentials(Integer loginCredentials) {
        this.loginCredentials = loginCredentials;
    }
}
