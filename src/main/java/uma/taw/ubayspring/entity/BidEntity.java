package uma.taw.ubayspring.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author Altair Bueno
 */

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bid", schema = "public", catalog = "UBAY")
@IdClass(BidEntityPK.class)

public class BidEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bid_id_generator")
    @SequenceGenerator(name = "bid_id_generator", sequenceName = "bid_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "publish_date", nullable = false)
    private Timestamp publishDate;
    @Basic
    @Column(name = "amount", nullable = false, precision = 0)
    private Double amount;
    @Id
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;
    @Id
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BidEntity)) return false;
        BidEntity bidEntity = (BidEntity) o;
        return Objects.equals(id, bidEntity.id) && Objects.equals(publishDate, bidEntity.publishDate) && Objects.equals(amount, bidEntity.amount) && Objects.equals(product, bidEntity.product) && Objects.equals(client, bidEntity.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, publishDate, amount, product, client);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
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
