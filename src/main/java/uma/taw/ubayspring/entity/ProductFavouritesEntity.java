package uma.taw.ubayspring.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Altair Bueno
 */

@Entity
@Table(name = "product_favourites", schema = "public", catalog = "UBAY")
public class ProductFavouritesEntity {
    @EmbeddedId
    ProductFavouritesEntityPK key;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("product")
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("client")
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductFavouritesEntity)) return false;
        ProductFavouritesEntity that = (ProductFavouritesEntity) o;
        return Objects.equals(key, that.key) && Objects.equals(product, that.product) && Objects.equals(client, that.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, product, client);
    }

    public ProductFavouritesEntityPK getKey() {
        return key;
    }

    public void setKey(ProductFavouritesEntityPK key) {
        this.key = key;
    }

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
}
