package uma.taw.ubayspring.repository;

import org.springframework.security.core.userdetails.User;
import uma.taw.ubayspring.dto.users.ProductDTO;
import uma.taw.ubayspring.entity.ClientEntity;
import uma.taw.ubayspring.entity.ProductEntity;
import uma.taw.ubayspring.entity.ProductFavouritesEntity;

import java.util.List;

public interface FavouritesRepositoryCustom {
    public List<ProductEntity> getClientFavouriteProducts(ClientEntity user);
    public ProductFavouritesEntity getTuple(ClientEntity client, ProductEntity product);
}
