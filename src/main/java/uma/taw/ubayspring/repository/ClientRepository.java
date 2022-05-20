package uma.taw.ubayspring.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import uma.taw.ubayspring.entity.ClientEntity;
import uma.taw.ubayspring.types.GenderEnum;

import java.util.List;

/**
 * @author Altair Bueno
 */

public interface ClientRepository extends PagingAndSortingRepository<ClientEntity, Integer> {
}
