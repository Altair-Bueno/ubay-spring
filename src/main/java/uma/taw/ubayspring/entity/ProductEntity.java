package uma.taw.ubayspring.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * @author Altair Bueno
 */

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product", schema = "public", catalog = "UBAY")
public class ProductEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_generator")
    @SequenceGenerator(name = "product_id_generator", sequenceName = "product_id_seq", allocationSize = 1)
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
    @Column(name = "images", length = 100)
    private String image;
    @Basic
    @Column(name = "close_date")
    private Date closeDate;
    @Basic
    @Column(name = "publish_date", nullable = false)
    private Timestamp publishDate;
    @ManyToOne
    @JoinColumn(name = "vendor_id", nullable = false)
    private ClientEntity vendedor;
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity categoryId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductEntity)) return false;
        ProductEntity that = (ProductEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(title, that.title) && Objects.equals(description, that.description) && Objects.equals(outPrice, that.outPrice) && Objects.equals(image, that.image) && Objects.equals(closeDate, that.closeDate) && Objects.equals(publishDate, that.publishDate) && Objects.equals(vendedor, that.vendedor) && Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, outPrice, image, closeDate, publishDate, vendedor, categoryId);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getOutPrice() {
        return outPrice;
    }

    public void setOutPrice(Double outPrice) {
        this.outPrice = outPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public Timestamp getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Timestamp publishDate) {
        this.publishDate = publishDate;
    }

    public ClientEntity getVendedor() {
        return vendedor;
    }

    public void setVendedor(ClientEntity vendedor) {
        this.vendedor = vendedor;
    }

    public CategoryEntity getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(CategoryEntity categoryId) {
        this.categoryId = categoryId;
    }
}
