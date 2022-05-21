package uma.taw.ubayspring.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Altair Bueno
 */

@Data
@Entity
@Table(name = "product_favourites", schema = "public", catalog = "UBAY")
public class ProductFavouritesEntity {
    @EmbeddedId
    ProductFavouritesEntityPK key = new ProductFavouritesEntityPK();

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("product")
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("client")
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;
}
