package uma.taw.ubayspring.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_favourites", schema = "public", catalog = "UBAY")
@IdClass(UserFavouritesEntityPK.class)
public class UserFavouritesEntity {
    @Id
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;
    @Id
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;
}
