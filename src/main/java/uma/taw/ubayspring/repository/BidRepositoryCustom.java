package uma.taw.ubayspring.repository;

/**
 * @author Francisco Javier Hernández
 */

import uma.taw.ubayspring.entity.BidEntity;
import uma.taw.ubayspring.entity.ClientEntity;
import uma.taw.ubayspring.entity.ProductEntity;

import java.sql.Date;
import java.util.List;
import java.util.stream.Stream;

public interface BidRepositoryCustom {

    Stream<BidEntity> getFilteredBidsFromVendor(ClientEntity vendor, int page, Date startDate, Date endDate, String productTitle, String clientName, String orderBy, boolean asc);

    Stream<BidEntity> getFilteredBidsFromUser(ClientEntity user, int page, Date startDate, Date endDate, String productTitle, String vendorName, String orderBy, boolean asc);

    BidEntity getHighestBidByProduct(ProductEntity product);

    List<BidEntity> productsBiddedClosedProducts(ClientEntity sesion);

    boolean isWinnerBid(ClientEntity client, BidEntity bid);
}
