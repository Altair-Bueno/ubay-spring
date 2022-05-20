package uma.taw.ubayspring.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Altair Bueno
 */

@Data
public class SessionEntityPK implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Id
    @Column(name = "login_id", nullable = false)
    private Integer loginCredentials;

}
