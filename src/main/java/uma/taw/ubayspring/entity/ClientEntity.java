package uma.taw.ubayspring.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

/**
 * @author Altair Bueno
 */

@Entity
@Table(name = "client", schema = "public", catalog = "UBAY")
public class ClientEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Basic
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
    @Basic
    @Column(name = "address", nullable = false, length = 100)
    private String address;
    @Basic
    @Column(name = "city", nullable = false, length = 100)
    private String city;
    @Basic
    @Column(name = "birth_date", nullable = false)
    private Date birthDate;
    @Basic
    @Column(name = "gender", nullable = false, length = 10)
    private String gender;
    @OneToMany(mappedBy = "clientByClientId")
    private Collection<BidEntity> bidsById;
    @OneToMany(mappedBy = "clientByClientId")
    private Collection<LoginCredentialsEntity> loginCredentialsById;
    @OneToMany(mappedBy = "clientByVendorId")
    private Collection<ProductEntity> productsById;
    @OneToMany(mappedBy = "clientByClientId")
    private Collection<ProductFavouritesEntity> productFavouritesById;
    @OneToMany(mappedBy = "clientByClientId")
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientEntity that = (ClientEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null)
            return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null)
            return false;
        if (address != null ? !address.equals(that.address) : that.address != null)
            return false;
        if (city != null ? !city.equals(that.city) : that.city != null)
            return false;
        if (birthDate != null ? !birthDate.equals(that.birthDate) : that.birthDate != null)
            return false;
        return gender != null ? gender.equals(that.gender) : that.gender == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        return result;
    }

    public Collection<BidEntity> getBidsById() {
        return bidsById;
    }

    public void setBidsById(Collection<BidEntity> bidsById) {
        this.bidsById = bidsById;
    }

    public Collection<LoginCredentialsEntity> getLoginCredentialsById() {
        return loginCredentialsById;
    }

    public void setLoginCredentialsById(Collection<LoginCredentialsEntity> loginCredentialsById) {
        this.loginCredentialsById = loginCredentialsById;
    }

    public Collection<ProductEntity> getProductsById() {
        return productsById;
    }

    public void setProductsById(Collection<ProductEntity> productsById) {
        this.productsById = productsById;
    }

    public Collection<ProductFavouritesEntity> getProductFavouritesById() {
        return productFavouritesById;
    }

    public void setProductFavouritesById(Collection<ProductFavouritesEntity> productFavouritesById) {
        this.productFavouritesById = productFavouritesById;
    }

    public Collection<UserFavouritesEntity> getUserFavouritesById() {
        return userFavouritesById;
    }

    public void setUserFavouritesById(Collection<UserFavouritesEntity> userFavouritesById) {
        this.userFavouritesById = userFavouritesById;
    }
}
