package uma.taw.ubayspring.entity;

import javax.persistence.*;
import java.util.Collection;

/**
 * @author Altair Bueno
 */

@Entity
@Table(name = "category", schema = "public", catalog = "UBAY")
public class CategoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "name", nullable = false, length = 20)
    private String name;
    @Basic
    @Column(name = "description", nullable = false, length = 250)
    private String description;
    @OneToMany(mappedBy = "categoryByCategoryId")
    private Collection<ProductEntity> productsById;
    @OneToMany(mappedBy = "categoryByCategoryId")
    private Collection<UserFavouritesEntity> userFavouritesById;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryEntity that = (CategoryEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null)
            return false;
        return description != null ? description.equals(that.description) : that.description == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public Collection<ProductEntity> getProductsById() {
        return productsById;
    }

    public void setProductsById(Collection<ProductEntity> productsById) {
        this.productsById = productsById;
    }

    public Collection<UserFavouritesEntity> getUserFavouritesById() {
        return userFavouritesById;
    }

    public void setUserFavouritesById(Collection<UserFavouritesEntity> userFavouritesById) {
        this.userFavouritesById = userFavouritesById;
    }
}
