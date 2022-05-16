package uma.taw.ubayspring.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import uma.taw.ubayspring.entity.ProductEntity;

/**
 * @author Altair Bueno
 */

public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, Integer> {
}
