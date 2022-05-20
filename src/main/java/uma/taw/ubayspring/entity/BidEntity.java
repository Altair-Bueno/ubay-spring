package uma.taw.ubayspring.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author Altair Bueno
 */

@Data
@Entity
@Table(name = "bid", schema = "public", catalog = "UBAY")
@IdClass(BidEntityPK.class)
public class BidEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
