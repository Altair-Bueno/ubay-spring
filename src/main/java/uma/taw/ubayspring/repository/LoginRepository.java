package uma.taw.ubayspring.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import uma.taw.ubayspring.entity.LoginCredentialsEntity;

/**
 * @author Altair Bueno
 */

public interface LoginRepository extends PagingAndSortingRepository<LoginCredentialsEntity, Integer> {
}
