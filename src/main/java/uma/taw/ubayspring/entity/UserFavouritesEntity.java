package uma.taw.ubayspring.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author Altair Bueno
 */

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

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserFavouritesEntity)) return false;
        UserFavouritesEntity that = (UserFavouritesEntity) o;
        return Objects.equals(category, that.category) && Objects.equals(client, that.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, client);
    }
}
