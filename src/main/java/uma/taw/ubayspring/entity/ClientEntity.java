package uma.taw.ubayspring.entity;

import lombok.Data;
import uma.taw.ubayspring.types.GenderEnum;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "client", schema = "public", catalog = "UBAY")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
}
