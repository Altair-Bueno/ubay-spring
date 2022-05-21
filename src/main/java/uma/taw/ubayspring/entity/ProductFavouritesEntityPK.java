package uma.taw.ubayspring.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Altair Bueno
 */

@Data
@Embeddable
public class ProductFavouritesEntityPK implements Serializable {
    @Column(name = "product_id", nullable = false)
    private int product;

    @Column(name = "client_id", nullable = false)
    private int client;
}
