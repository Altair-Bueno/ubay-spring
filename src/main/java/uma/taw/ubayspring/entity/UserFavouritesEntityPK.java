package uma.taw.ubayspring.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class UserFavouritesEntityPK implements Serializable {
    @Id
    @Column(name = "category_id", nullable = false)
    private Integer category;
    @Id
    @Column(name = "client_id", nullable = false)
    private Integer client;
}
