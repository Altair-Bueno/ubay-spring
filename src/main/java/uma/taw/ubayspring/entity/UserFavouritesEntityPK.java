package uma.taw.ubayspring.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Altair Bueno
 */

public class UserFavouritesEntityPK implements Serializable {
    //@Id
    //@Column(name = "category_id", nullable = false)
    private Integer category;
    //@Id
    //@Column(name = "client_id", nullable = false)
    private Integer client;

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserFavouritesEntityPK)) return false;
        UserFavouritesEntityPK that = (UserFavouritesEntityPK) o;
        return Objects.equals(category, that.category) && Objects.equals(client, that.client);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, client);
    }
}
