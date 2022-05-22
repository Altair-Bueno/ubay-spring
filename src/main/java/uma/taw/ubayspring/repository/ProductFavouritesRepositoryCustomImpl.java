package uma.taw.ubayspring.repository;

import org.springframework.stereotype.Repository;
import uma.taw.ubayspring.entity.CategoryEntity;
import uma.taw.ubayspring.entity.ClientEntity;
import uma.taw.ubayspring.entity.ProductEntity;
import uma.taw.ubayspring.entity.ProductFavouritesEntity;
import uma.taw.ubayspring.keys.ProductKeys;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Repository
public class ProductFavouritesRepositoryCustomImpl {

    @PersistenceContext
    private EntityManager em;

    /**
     * @author José Luis Bueno Pachón
     */
    public List<ProductEntity> getClientFavouriteProducts(ClientEntity client) {
        try {
            return em.createQuery("SELECT p.product FROM ProductFavouritesEntity p WHERE p.client = :client", ProductEntity.class)
                    .setParameter("client", client)
                    .getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    /**
     * @author Francisco Javier Hernández
     */
    public ProductRepositoryCustomImpl.ProductTupleResult<ProductEntity> getClientFavouriteProductsFiltered(ClientEntity clientEntity, String name, CategoryEntity category, int page) {

        if (clientEntity == null) return null;

        CriteriaBuilder builder = em.getCriteriaBuilder();
        List<Predicate> predicateList = new ArrayList<>();
        CriteriaQuery<ProductEntity> query = builder.createQuery(ProductEntity.class);
        Root<ProductFavouritesEntity> productFavTable = query.from(ProductFavouritesEntity.class);
        Root<ProductEntity> productTable = query.from(ProductEntity.class);

        int actualSize = 0;

        if (name != null) {
            predicateList.add(builder.like(builder.upper(productTable.get("title")), "%" + name.toUpperCase(Locale.ROOT) + "%"));
        }

        if (category != null) {
            predicateList.add(builder.equal(productTable.get("category"), category));
        }

        predicateList.add(builder.equal(productFavTable.get("user"), clientEntity));
        predicateList.add(builder.equal(productTable.get("id"), productFavTable.get("product").get("id")));

        query.select(productTable).where(predicateList.toArray(new Predicate[0]));

        actualSize = em.createQuery(query)
                .getResultList()
                .size();

        List<ProductEntity> productEntities = em.createQuery(query)
                .setFirstResult(page * ProductKeys.productsPerPageLimit)
                .setMaxResults(ProductKeys.productsPerPageLimit)
                .getResultList();

        return new ProductRepositoryCustomImpl.ProductTupleResult<>(productEntities, actualSize);
    }

    /**
     * @author Francisco Javier Hernández
     */
    public ProductRepositoryCustomImpl.ProductTupleResult<ProductEntity> getClientFavouriteProductsByPage(int page) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        List<Predicate> predicateList = new ArrayList<>();
        CriteriaQuery<ProductEntity> query = builder.createQuery(ProductEntity.class);
        Root<ProductFavouritesEntity> productFavTable = query.from(ProductFavouritesEntity.class);
        Join<ProductFavouritesEntity, ProductEntity> join = productFavTable.join("product", JoinType.INNER);
        int actualSize = 0;

        query.select(join)
                .orderBy(builder.desc(join.get("id")));

        actualSize = em.createQuery(query)
                .getResultList()
                .size();

        List<ProductEntity> productEntities = em.createQuery(query)
                .setFirstResult(page * ProductKeys.productsPerPageLimit)
                .setMaxResults(ProductKeys.productsPerPageLimit)
                .getResultList();

        return new ProductRepositoryCustomImpl.ProductTupleResult(productEntities, actualSize);

    }

    /**
     * @author Francisco Javier Hernández
     */
    public ProductFavouritesEntity getTuple(ClientEntity client, ProductEntity product) {
        return em.createQuery("SELECT p FROM ProductFavouritesEntity p WHERE p.client = :client AND p.product = :product", ProductFavouritesEntity.class)
                .setParameter("client", client)
                .setParameter("product", product)
                .getSingleResult();
    }
}
