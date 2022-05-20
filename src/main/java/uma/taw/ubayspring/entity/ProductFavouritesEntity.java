package uma.taw.ubayspring.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author Altair Bueno
 */

@Data
@Entity
@Table(name = "product_favourites", schema = "public", catalog = "UBAY")
@IdClass(ProductFavouritesEntityPK.class)
public class ProductFavouritesEntity {
    @Id
    @ManyToOne
    @Column(name = "product_id", nullable = false)
    private ProductEntity product;
    @Id
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;
}
