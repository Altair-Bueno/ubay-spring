package uma.taw.ubayspring.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import uma.taw.ubayspring.entity.ProductFavouritesEntity;
import uma.taw.ubayspring.entity.ProductFavouritesEntityPK;

public interface ProductFavouritesRepository extends PagingAndSortingRepository<ProductFavouritesEntity, ProductFavouritesEntityPK> {
}
