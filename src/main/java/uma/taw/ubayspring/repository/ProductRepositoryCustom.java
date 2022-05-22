package uma.taw.ubayspring.repository;

import uma.taw.ubayspring.entity.CategoryEntity;
import uma.taw.ubayspring.entity.ClientEntity;

public interface ProductRepositoryCustom {
    public ProductRepositoryCustomImpl.ProductTupleResult filterAndGetByPage(ClientEntity client, String name, CategoryEntity category, boolean owned, int page);


}
