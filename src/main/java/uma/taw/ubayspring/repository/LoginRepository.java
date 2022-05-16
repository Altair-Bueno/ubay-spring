package uma.taw.ubayspring.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import uma.taw.ubayspring.entity.LoginCredentialsEntity;

public interface LoginRepository extends PagingAndSortingRepository<LoginCredentialsEntity,Integer> {
}
