package uma.taw.ubayspring.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class BidEntityPK implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Id
    @Column(name = "product_id", nullable = false)
    private Integer product;
    @Id
    @Column(name = "client_id", nullable = false)
    private Integer client;
}
