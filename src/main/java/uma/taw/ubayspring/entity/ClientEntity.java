package uma.taw.ubayspring.entity;

import uma.taw.ubayspring.types.GenderEnum;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

/**
 * @author Altair Bueno
 */

@Entity
@Table(name = "client", schema = "public", catalog = "UBAY")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_id_generator")
    @SequenceGenerator(name = "client_id_generator", sequenceName = "client_id_seq", allocationSize = 1)
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
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false, length = 10)
    private GenderEnum gender;

    public ClientEntity() {
    }

    public ClientEntity(String name, String lastName, String address, String city, Date birthDate, GenderEnum gender) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ClientEntity)) return false;
        ClientEntity that = (ClientEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName) && Objects.equals(address, that.address) && Objects.equals(city, that.city) && Objects.equals(birthDate, that.birthDate) && gender == that.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, address, city, birthDate, gender);
    }

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

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }
}
