package uma.taw.ubayspring.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import uma.taw.ubayspring.entity.ClientEntity;

/**
 * @author Altair Bueno
 */

public interface ClientRepository extends PagingAndSortingRepository<ClientEntity, Integer> {
}
