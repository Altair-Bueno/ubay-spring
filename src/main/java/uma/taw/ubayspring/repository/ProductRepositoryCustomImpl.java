package uma.taw.ubayspring.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import uma.taw.ubayspring.entity.CategoryEntity;
import uma.taw.ubayspring.entity.ClientEntity;
import uma.taw.ubayspring.entity.ProductEntity;
import uma.taw.ubayspring.keys.ProductKeys;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom{

    @PersistenceContext
    EntityManager em;

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductTupleResult filterAndGetByPage(ClientEntity vendedor, String title, CategoryEntity category, boolean owned, int page){
        if(page == 0) page = 1;
        int beginning = ProductKeys.productsPerPageLimit * (page - 1), end = (ProductKeys.productsPerPageLimit * page);

        ExampleMatcher matcher = ExampleMatcher
                .matchingAll()
                .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase());
        ProductEntity example = ProductEntity
                .builder()
                .vendedor(owned ? vendedor : null)
                .title(title)
                .categoryId(category)
                .build();

        List<ProductEntity> productEntities = productRepository.findAll(Example.of(example, matcher));
        int size = productEntities.size();
        if(end > size) end = size;
        productEntities = productEntities.subList(beginning, end);

        return new ProductTupleResult(productEntities, size);
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
