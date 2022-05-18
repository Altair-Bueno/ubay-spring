package uma.taw.ubayspring.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "category", schema = "public", catalog = "UBAY")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic
    @Column(name = "name", nullable = false, length = 20)
    private String name;
    @Basic
    @Column(name = "description", nullable = false, length = 250)
    private String description;

}
