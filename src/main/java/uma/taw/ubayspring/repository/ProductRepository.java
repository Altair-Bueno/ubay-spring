package uma.taw.ubayspring.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import uma.taw.ubayspring.entity.ProductEntity;

public interface ProductRepository extends PagingAndSortingRepository<ProductEntity,Integer> {
}
