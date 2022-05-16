package uma.taw.ubayspring.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import uma.taw.ubayspring.entity.PasswordResetEntity;
import uma.taw.ubayspring.entity.PasswordResetEntityPK;

public interface PasswordResetRepository extends PagingAndSortingRepository<PasswordResetEntity, PasswordResetEntityPK> {
}
