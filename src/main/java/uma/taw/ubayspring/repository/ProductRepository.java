package uma.taw.ubayspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import uma.taw.ubayspring.entity.ProductEntity;

/**
 * @author Francisco Javier Hernández
 */

public interface ProductRepository extends PagingAndSortingRepository<ProductEntity, Integer>, JpaRepository<ProductEntity, Integer> {
}
