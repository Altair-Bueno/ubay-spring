package uma.taw.ubayspring.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "product", schema = "public", catalog = "UBAY")
public class ProductEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "title", nullable = false, length = 50)
    private String title;
    @Basic
    @Column(name = "description", nullable = false, length = 250)
    private String description;
    @Basic
    @Column(name = "out_price", nullable = false, precision = 0)
    private Double outPrice;
    @Basic
    @Column(name = "images", nullable = true, length = 100)
    private String image;
    @Basic
    @Column(name = "close_date", nullable = true)
    private Date closeDate;
    @Basic
    @Column(name = "publish_date", nullable = false)
    private Timestamp publishDate;
    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    private ClientEntity vendedor;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private ClientEntity categoryId;
}
