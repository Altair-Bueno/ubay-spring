package uma.taw.ubayspring.repository;

import uma.taw.ubayspring.entity.CategoryEntity;
import uma.taw.ubayspring.entity.ClientEntity;
import uma.taw.ubayspring.entity.ProductEntity;
import uma.taw.ubayspring.entity.ProductFavouritesEntity;

import java.util.List;

public interface ProductFavouritesRepositoryCustom {
    public List<ProductEntity> getClientFavouriteProducts(ClientEntity client);

    public ProductRepositoryCustomImpl.ProductTupleResult<ProductEntity> getClientFavouriteProductsFiltered(ClientEntity clientEntity, String name, CategoryEntity category, int page);

    public ProductRepositoryCustomImpl.ProductTupleResult<ProductEntity> getClientFavouriteProductsByPage(int page);

    public ProductFavouritesEntity getTuple(ClientEntity client, ProductEntity product);
}
