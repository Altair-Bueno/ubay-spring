package uma.taw.ubayspring.repository;

import org.springframework.stereotype.Repository;
import uma.taw.ubayspring.entity.CategoryEntity;
import uma.taw.ubayspring.entity.ClientEntity;
import uma.taw.ubayspring.entity.ProductEntity;
import uma.taw.ubayspring.keys.ProductKeys;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Repository
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom{

    @PersistenceContext
    EntityManager em;

    @Override
    public ProductTupleResult filterAndGetByPage(ClientEntity client, String name, CategoryEntity category, boolean owned, int page) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<ProductEntity> query = builder.createQuery(ProductEntity.class);
        Root<ProductEntity> productTable = query.from(ProductEntity.class);
        List<Predicate> predicateList = new ArrayList<>();
        query.select(productTable);
        int actualSize = 0;

        if(name != null){
            predicateList.add(builder.like(builder.upper(productTable.get("title")), "%" + name.toUpperCase(Locale.ROOT) + "%"));
        }

        if(category != null){
            predicateList.add(builder.equal(productTable.get("category"), category));
        }

        if(client != null && owned){
            predicateList.add(builder.equal(productTable.get("vendor"), client));
        }

        query.select(productTable).where(predicateList.toArray(new Predicate[0]));

        actualSize = em.createQuery(query)
                .getResultList()
                .size();

        List<ProductEntity> productEntities = em.createQuery(query)
                .setFirstResult(page * ProductKeys.productsPerPageLimit)
                .setMaxResults(ProductKeys.productsPerPageLimit)
                .getResultList();

        return new ProductTupleResult<>(productEntities, actualSize);
    }

    public static class ProductTupleResult<T>{
        private List<T> productEntities;
        private int actualSize = 0;

        public ProductTupleResult(List<T> pE, int aS){
            productEntities = pE;
            actualSize = aS;
        }

        public List<T> getProductEntities() {
            return productEntities;
        }

        public int getActualSize() {
            return actualSize;
        }
    }
}
