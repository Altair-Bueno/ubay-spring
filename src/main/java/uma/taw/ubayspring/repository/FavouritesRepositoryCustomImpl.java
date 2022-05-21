package uma.taw.ubayspring.repository;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;
import uma.taw.ubayspring.dto.users.ProductDTO;
import uma.taw.ubayspring.entity.ClientEntity;
import uma.taw.ubayspring.entity.ProductEntity;
import uma.taw.ubayspring.entity.ProductFavouritesEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FavouritesRepositoryCustomImpl implements FavouritesRepositoryCustom{

    @PersistenceContext
    private EntityManager em;
    @Override
    public List<ProductEntity> getClientFavouriteProducts(ClientEntity client) {
        try {
            return em.createQuery("SELECT p.product FROM ProductFavouritesEntity p WHERE p.client= :user", ProductEntity.class)
                    .setParameter("user", client)
                    .getResultList();
        } catch (Exception e){
            return new ArrayList<>();
        }
    }

    public ProductFavouritesEntity getTuple(ClientEntity client, ProductEntity product){
        return em.createQuery("SELECT p FROM ProductFavouritesEntity p WHERE p.client = :user AND p.product = :product", ProductFavouritesEntity.class)
                .setParameter("user", client)
                .setParameter("product", product)
                .getSingleResult();
    }
}
