package uma.taw.ubayspring.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Altair Bueno
 */

@Embeddable
public class ProductFavouritesEntityPK implements Serializable {
    @Column(name = "product_id", nullable = false)
    private Integer product;

    @Column(name = "client_id", nullable = false)
    private Integer client;

    public int getProduct() {
        return product;
    }

    public void setProduct(Integer product) {
        this.product = product;
    }

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductFavouritesEntityPK)) return false;
        ProductFavouritesEntityPK that = (ProductFavouritesEntityPK) o;
        return product == that.product && client == that.client;
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, client);
    }
}
