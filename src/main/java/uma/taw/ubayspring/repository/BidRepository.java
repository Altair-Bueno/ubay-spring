package uma.taw.ubayspring.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import uma.taw.ubayspring.entity.BidEntity;
import uma.taw.ubayspring.entity.BidEntityPK;

public interface BidRepository extends PagingAndSortingRepository<BidEntity, BidEntityPK> {

}
