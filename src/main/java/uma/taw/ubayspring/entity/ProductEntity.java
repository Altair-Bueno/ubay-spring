package uma.taw.ubayspring.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collection;

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
    private String images;
    @Basic
    @Column(name = "close_date", nullable = true)
    private Date closeDate;
    @Basic
    @Column(name = "publish_date", nullable = false)
    private Timestamp publishDate;
    @Basic
    @Column(name = "vendor_id", nullable = false)
    private Integer vendorId;
    @Basic
    @Column(name = "category_id", nullable = false)
    private Integer categoryId;
    @OneToMany(mappedBy = "productByProductId")
    private Collection<BidEntity> bidsById;
    @ManyToOne
    @JoinColumn(name = "vendor_id", referencedColumnName = "id", nullable = false, insertable = false,updatable = false)
    private ClientEntity clientByVendorId;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false, insertable = false,updatable = false)
    private CategoryEntity categoryByCategoryId;
    @OneToMany(mappedBy = "productByProductId")
    private Collection<ProductFavouritesEntity> productFavouritesById;

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

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
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

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductEntity that = (ProductEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        if (outPrice != null ? !outPrice.equals(that.outPrice) : that.outPrice != null)
            return false;
        if (images != null ? !images.equals(that.images) : that.images != null)
            return false;
        if (closeDate != null ? !closeDate.equals(that.closeDate) : that.closeDate != null)
            return false;
        if (publishDate != null ? !publishDate.equals(that.publishDate) : that.publishDate != null)
            return false;
        if (vendorId != null ? !vendorId.equals(that.vendorId) : that.vendorId != null)
            return false;
        if (categoryId != null ? !categoryId.equals(that.categoryId) : that.categoryId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (outPrice != null ? outPrice.hashCode() : 0);
        result = 31 * result + (images != null ? images.hashCode() : 0);
        result = 31 * result + (closeDate != null ? closeDate.hashCode() : 0);
        result = 31 * result + (publishDate != null ? publishDate.hashCode() : 0);
        result = 31 * result + (vendorId != null ? vendorId.hashCode() : 0);
        result = 31 * result + (categoryId != null ? categoryId.hashCode() : 0);
        return result;
    }

    public Collection<BidEntity> getBidsById() {
        return bidsById;
    }

    public void setBidsById(Collection<BidEntity> bidsById) {
        this.bidsById = bidsById;
    }

    public ClientEntity getClientByVendorId() {
        return clientByVendorId;
    }

    public void setClientByVendorId(ClientEntity clientByVendorId) {
        this.clientByVendorId = clientByVendorId;
    }

    public CategoryEntity getCategoryByCategoryId() {
        return categoryByCategoryId;
    }

    public void setCategoryByCategoryId(CategoryEntity categoryByCategoryId) {
        this.categoryByCategoryId = categoryByCategoryId;
    }

    public Collection<ProductFavouritesEntity> getProductFavouritesById() {
        return productFavouritesById;
    }

    public void setProductFavouritesById(Collection<ProductFavouritesEntity> productFavouritesById) {
        this.productFavouritesById = productFavouritesById;
    }
}
