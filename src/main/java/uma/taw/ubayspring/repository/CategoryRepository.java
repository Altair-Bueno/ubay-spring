package uma.taw.ubayspring.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import uma.taw.ubayspring.entity.CategoryEntity;

/**
 * @author Altair Bueno
 */

public interface CategoryRepository extends PagingAndSortingRepository<CategoryEntity, Integer> {
}
