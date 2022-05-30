package uma.taw.ubayspring.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
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
import java.util.Optional;

@Repository
public class ProductFavouritesRepositoryCustomImpl implements ProductFavouritesRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    ProductFavouritesRepository productFavouritesRepository;

    @Autowired
    ProductRepository productRepository;

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
    public ProductRepositoryCustomImpl.ProductTupleResult<ProductEntity> getClientFavouriteProductsFiltered(ClientEntity client, String title, CategoryEntity category, int page) {

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());

        List<ProductEntity> productEntityList = new ArrayList<>();
        ProductEntity productExample = ProductEntity
                .builder()
                .title(title)
                .categoryId(category)
                .build();
        List<ProductEntity> productsFound = productRepository.findAll(Example.of(productExample, matcher));

        int cont = 0;
        for(ProductEntity p : productsFound){
            ProductFavouritesEntity example = ProductFavouritesEntity
                    .builder()
                    .client(client)
                    .product(p)
                    .build();
            Optional<ProductFavouritesEntity> queryResult = productFavouritesRepository.findOne(Example.of(example));
            if(queryResult.isPresent() && cont < ProductKeys.productsPerPageLimit){
                productEntityList.add(p);
            }
            cont++;
        }

        return new ProductRepositoryCustomImpl.ProductTupleResult<>(productEntityList, cont);
    }

    /**
     * @author Francisco Javier Hernández
     */
    public ProductFavouritesEntity getTuple(ClientEntity client, ProductEntity product) {
        ProductFavouritesEntity example = ProductFavouritesEntity
                .builder()
                .client(client)
                .product(product)
                .build();
        return productFavouritesRepository.findOne(Example.of(example)).get();
    }
}
