package uma.taw.ubayspring.repository;

/**
 * @author Francisco Javier Hernández
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import uma.taw.ubayspring.entity.BidEntity;
import uma.taw.ubayspring.entity.ClientEntity;
import uma.taw.ubayspring.entity.ProductEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Repository
public class BidRepositoryCustomImpl implements BidRepositoryCustom{
    @PersistenceContext
    private EntityManager em;

    @Autowired
    BidRepository bidRepository;

    public Stream<BidEntity> getFilteredBidsFromVendor(ClientEntity vendor, int page, Date startDate, Date endDate, String productTitle, String clientName, String orderBy, boolean asc) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<BidEntity> query = builder.createQuery(BidEntity.class);

        // select bidTable.*
        // from BidEntity bidTable, ProductEntity productTable, ClientEntity clientTable
        // where productTable.vendor = :vendor and
        //      bidTable.product = productTable and
        //      bidTable.client = clientTable
        // order by bidTable.publish_date
        Root<BidEntity> bidTable = query.from(BidEntity.class);
        Root<ProductEntity> productTable = query.from(ProductEntity.class);
        Root<ClientEntity> clientTable = query.from(ClientEntity.class);

        List<Predicate> predicateList = new ArrayList<>();
        predicateList.add(builder.equal(bidTable.get("product"),productTable));
        predicateList.add(builder.equal(bidTable.get("client"),clientTable));
        predicateList.add(builder.equal(productTable.get("vendedor"), vendor));
        if (startDate != null) predicateList.add(builder.greaterThanOrEqualTo(bidTable.get("publishDate"),startDate));
        if (endDate != null) predicateList.add(builder.lessThanOrEqualTo(bidTable.get("publishDate"),endDate));
        if (productTitle != null) predicateList.add(builder.like(builder.lower(productTable.get("title")),"%" + productTitle.toLowerCase() + "%"));
        if (clientName != null) predicateList.add(builder.like(builder.lower(clientTable.get("name")),"%"+ clientName.toLowerCase() + "%"));

        query.select(bidTable)
                .where(predicateList.toArray(new Predicate[0]))
                .orderBy(
                        asc ?
                                builder.asc(bidTable.get(orderBy)):
                                builder.desc(bidTable.get(orderBy))
                );
        return em.createQuery(query)
                .setFirstResult(page * 10)
                .setMaxResults(10)
                .getResultStream();
    }

    public Stream<BidEntity> getFilteredBidsFromUser(ClientEntity client, int page, Date startDate, Date endDate, String productTitle, String vendorName,String orderBy,boolean asc) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<BidEntity> query = builder.createQuery(BidEntity.class);

        Root<BidEntity> bidTable = query.from(BidEntity.class);
        Root<ProductEntity> productTable = query.from(ProductEntity.class);
        Root<ClientEntity> clientTable = query.from(ClientEntity.class);

        List<Predicate> predicateList = new ArrayList<>();
        predicateList.add(builder.equal(bidTable.get("client"), client));
        predicateList.add(builder.equal(bidTable.get("product"),productTable));
        predicateList.add(builder.equal(productTable.get("vendedor"),clientTable));
        if (startDate != null) predicateList.add(builder.greaterThanOrEqualTo(bidTable.get("publishDate"),startDate));
        if (endDate != null) predicateList.add(builder.lessThanOrEqualTo(bidTable.get("publishDate"),endDate));
        if (productTitle != null) predicateList.add(builder.like(builder.lower(productTable.get("title")),"%" + productTitle.toLowerCase() + "%"));
        if (vendorName != null) predicateList.add(builder.like(builder.lower(clientTable.get("name")),"%"+ vendorName.toLowerCase() + "%"));

        query.select(bidTable)
                .where(predicateList.toArray(new Predicate[0]))
                .orderBy(
                        asc ?
                                builder.asc(bidTable.get(orderBy)):
                                builder.desc(bidTable.get(orderBy))
                );
        return em.createQuery(query)
                .setFirstResult(page * 10)
                .setMaxResults(10)
                .getResultStream();
    }

    public BidEntity getHighestBidByProduct(ProductEntity product) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<BidEntity> query = builder.createQuery(BidEntity.class);

        // select bidTable
        // from BidEntity bidTable
        // where bidTable.product = :product
        // having bidTable.amount = max(bidTable.amount)
        Root<BidEntity> bidTable = query.from(BidEntity.class);
        query
                .select(bidTable)
                .where(builder.equal(bidTable.get("product"), product.getId()))
                .orderBy(builder.desc(bidTable.get("amount")));

        List<BidEntity> resultList = em.createQuery(query)
                .setMaxResults(1).getResultList();
        return resultList == null || resultList.isEmpty() ? null : resultList.get(0);
    }

    public List<BidEntity> productsBiddedClosedProducts(ClientEntity sesion){
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<BidEntity> query = builder.createQuery(BidEntity.class);
        Root<BidEntity> bidTable = query.from(BidEntity.class);
        Join<BidEntity, ProductEntity> join = bidTable.join("product", JoinType.INNER);

        List<Predicate> predicateList = new ArrayList<>();
        predicateList.add(builder.equal(bidTable.get("client"), sesion));
        predicateList.add(builder.isNotNull(join.get("closeDate")));
        predicateList.add(builder.lessThanOrEqualTo(join.get("closeDate"), new java.util.Date()));

        query.select(bidTable)
                .where(predicateList.toArray(new Predicate[0]))
                .orderBy(builder.desc(join.get("closeDate")));

        return em.createQuery(query)
                .getResultList();
    }

    public boolean isWinnerBid(ClientEntity client, BidEntity bid){
        return getHighestBidByProduct(bid.getProduct()).getClient().equals(client);
    }
}
