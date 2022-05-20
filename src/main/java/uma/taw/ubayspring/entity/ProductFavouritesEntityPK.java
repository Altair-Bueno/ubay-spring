package uma.taw.ubayspring.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Altair Bueno
 */

@Data
public class ProductFavouritesEntityPK implements Serializable {
    @Id
    @Column(name = "product_id", nullable = false)
    private Integer product;
    @Id
    @Column(name = "client_id", nullable = false)
    private Integer client;
}
