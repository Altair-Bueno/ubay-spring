package uma.taw.ubayspring.repository;

import uma.taw.ubayspring.entity.ClientEntity;
import uma.taw.ubayspring.entity.ProductEntity;
import uma.taw.ubayspring.entity.ProductFavouritesEntity;

import java.util.List;

public interface ProductFavouritesRepositoryCustom {
    public List<ProductEntity> getClientFavouriteProducts(ClientEntity user);
    public ProductFavouritesEntity getTuple(ClientEntity client, ProductEntity product);
}
