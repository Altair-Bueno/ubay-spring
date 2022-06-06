package uma.taw.ubayspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import uma.taw.ubayspring.entity.ProductFavouritesEntity;
import uma.taw.ubayspring.entity.ProductFavouritesEntityPK;

/**
 * @author Francisco Javier Hernández
 */

public interface ProductFavouritesRepository
        extends PagingAndSortingRepository<ProductFavouritesEntity, ProductFavouritesEntityPK>,
        JpaRepository<ProductFavouritesEntity, ProductFavouritesEntityPK> {
}
