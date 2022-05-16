package uma.taw.ubayspring.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import uma.taw.ubayspring.entity.UserFavouritesEntity;
import uma.taw.ubayspring.entity.UserFavouritesEntityPK;

/**
 * @author Altair Bueno
 */

public interface UserFavouritesRepository extends PagingAndSortingRepository<UserFavouritesEntity, UserFavouritesEntityPK> {
}
