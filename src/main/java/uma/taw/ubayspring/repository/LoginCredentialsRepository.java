package uma.taw.ubayspring.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import uma.taw.ubayspring.entity.LoginCredentialsEntity;

/**
 * @author Altair Bueno
 */

public interface LoginCredentialsRepository extends PagingAndSortingRepository<LoginCredentialsEntity, Integer> {
    LoginCredentialsEntity findLoginCredentialsEntityByUsername(String username);
}
