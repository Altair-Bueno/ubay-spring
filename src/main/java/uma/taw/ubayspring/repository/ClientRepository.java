package uma.taw.ubayspring.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import uma.taw.ubayspring.entity.ClientEntity;

public interface ClientRepository extends PagingAndSortingRepository<ClientEntity,Integer> {
}
