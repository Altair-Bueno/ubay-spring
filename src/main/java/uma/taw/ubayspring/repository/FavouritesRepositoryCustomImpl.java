package uma.taw.ubayspring.repository;

import org.springframework.stereotype.Repository;
import uma.taw.ubayspring.entity.CategoryEntity;
import uma.taw.ubayspring.entity.ClientEntity;
import uma.taw.ubayspring.entity.ProductEntity;
import uma.taw.ubayspring.entity.ProductFavouritesEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: José Luis Bueno Pachón
 */

@Repository
public class FavouritesRepositoryCustomImpl implements FavouritesRepositoryCustom{

    @PersistenceContext
    private EntityManager em;

    public List<CategoryEntity> getClientFavouriteCategories(ClientEntity client){
        try {
            return em.createQuery("SELECT c.category FROM UserFavouritesEntity c WHERE c.client = :user", CategoryEntity.class)
                    .setParameter("user", client)
                    .getResultList();
        } catch (Exception e){
            return new ArrayList<>();
        }
    }
}
