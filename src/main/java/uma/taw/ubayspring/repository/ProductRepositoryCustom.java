package uma.taw.ubayspring.repository;

/**
 * @author Francisco Javier Hernández
 */

import uma.taw.ubayspring.entity.CategoryEntity;
import uma.taw.ubayspring.entity.ClientEntity;

public interface ProductRepositoryCustom {
    public ProductRepositoryCustomImpl.ProductTupleResult filterAndGetByPage(ClientEntity client, String name, CategoryEntity category, boolean owned, int page);


}
