package uma.taw.ubayspring.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import uma.taw.ubayspring.entity.SessionEntity;
import uma.taw.ubayspring.entity.SessionEntityPK;

public interface SessionRepository extends PagingAndSortingRepository<SessionEntity, SessionEntityPK> {
}
