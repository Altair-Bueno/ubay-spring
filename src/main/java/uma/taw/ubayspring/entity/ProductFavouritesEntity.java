package uma.taw.ubayspring.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Altair Bueno
 */

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_favourites", schema = "public", catalog = "UBAY")
@IdClass(ProductFavouritesEntityPK.class)
public class ProductFavouritesEntity {
//    @EmbeddedId
//    ProductFavouritesEntityPK key;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    //@MapsId("product")
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    //@MapsId("client")
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductFavouritesEntity)) return false;
        ProductFavouritesEntity that = (ProductFavouritesEntity) o;
        return Objects.equals(product, that.product) && Objects.equals(client, that.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, client);
    }
}
