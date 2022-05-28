package uma.taw.ubayspring.service.products;

import com.jlefebure.spring.boot.minio.MinioException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uma.taw.ubayspring.dto.products.*;
import uma.taw.ubayspring.dto.products.ProductForm.ProductFormParamsDTO;
import uma.taw.ubayspring.dto.products.index.ParamsDTO;
import uma.taw.ubayspring.entity.*;
import uma.taw.ubayspring.repository.*;
import uma.taw.ubayspring.service.AuthService;
import uma.taw.ubayspring.service.UsersService;
import uma.taw.ubayspring.types.KindEnum;
import uma.taw.ubayspring.wrapper.MinioWrapperService;

import javax.servlet.ServletException;
import javax.servlet.http.Part;
import javax.swing.text.html.Option;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @author Francisco Javier Hernández
 */

@Service
public class ProductService {
    @Autowired
    BidRepositoryCustom bidRepositoryCustom;

    @Autowired
    UsersService usersService;

    @Autowired
    ProductRepositoryCustom productRepositoryCustom;
    
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductFavouritesRepositoryCustom productFavouritesRepositoryCustom;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    LoginCredentialsRepository loginCredentialsRepository;

    @Autowired
    MinioWrapperService minioWrapperService;

    @Autowired
    AuthService authService;


    public ProductsDTO getProductsList(ParamsDTO paramsDTO, ProductClientDTO sesionClient) {
        String favOwnedFilter = paramsDTO.getFavOwnedFilter() == null ? "" : paramsDTO.getFavOwnedFilter(), productName = paramsDTO.getName() == null ? "" : paramsDTO.getName();
        int category = paramsDTO.getCategory(), page = paramsDTO.getPage();

        boolean favFilter = favOwnedFilter.equals("favFilter"), ownedFilter = favOwnedFilter.equals("ownedFilter");
        ClientEntity clientEntity = null;
        ProductRepositoryCustomImpl.ProductTupleResult<ProductEntity> ptr;
        List<ProductDTO> productDTOS = new ArrayList<>();

        // Set client
        if(sesionClient != null) {
            clientEntity = clientRepository.findById(sesionClient.getId()).get();
        }

        Optional<CategoryEntity> optCategory = categoryRepository.findById(category);
        CategoryEntity categoryEntity = optCategory.isPresent() ? optCategory.get() : null;

        // Filters:
        if(favFilter){
            ptr = productFavouritesRepositoryCustom.getClientFavouriteProductsFiltered(clientEntity, productName, categoryEntity, page);
        } else {
            ptr = productRepositoryCustom.filterAndGetByPage(clientEntity, productName, categoryEntity, ownedFilter, page);
        }

        for (ProductEntity p : ptr.getProductEntities()) {
            productDTOS.add(
                    productEntityToDTO(p)
            );
        }

        return new ProductsDTO(productDTOS, ptr.getActualSize());
    }

    @NonNull
    public boolean isProductUserFavourite(ProductClientDTO client, Integer id) {
        ClientEntity user = clientRepository.findById(client.getId()).get();

        if (user != null) {
            List<ProductEntity> products = productFavouritesRepositoryCustom.getClientFavouriteProducts(user);
            var product = productRepository.findById(id).get();
            return products.contains(product);
        }

        return false;
    }

    @NonNull
    public ProductCategoryDTO findByIdCategoryId(int id) {
        return categoryEntityToDTO(categoryRepository.findById(id).get());
    }

    public Integer createProduct(ProductFormParamsDTO paramsDTO, ProductClientDTO sesionClient) throws IOException, ServletException {
        ClientEntity vendedorEntity = clientRepository.findById(sesionClient.getId()).get();
        CategoryEntity categoryEntity = categoryRepository.findById(paramsDTO.getCategory()).get();
        var file = paramsDTO.getImage();
        String imgName = null;

        // IMAGEN
        if (file != null && !file.isEmpty()) {
            InputStream inputStream = file.getInputStream();


            try {
                imgName = minioWrapperService.uploadObject(inputStream);
            } catch (Exception e) {
                throw new ServletException(e.getStackTrace().toString());
            }
        }

        ProductEntity p = ProductEntity
                .builder()
                .title(paramsDTO.getTitle())
                .description(paramsDTO.getDescription())
                .outPrice(paramsDTO.getPrice())
                .image(imgName)
                .closeDate(null)
                .publishDate(new Timestamp(new java.util.Date().getTime()))
                .categoryId(categoryEntity)
                .vendedor(vendedorEntity)
                .build();

        productRepository.save(p);
        p = productRepository.findOne(Example.of(p)).get();

        return p.getId();
    }

    public ProductDTO findByIdProduct(int id) {
        return productEntityToDTO(productRepository.findById(id).get());
    }

    @NonNull
    public List<ProductCategoryDTO> categories() {
        return StreamSupport
                .stream(categoryRepository.findAll(Sort.by(Sort.Direction.ASC, "id")).spliterator(), false)
                .map(this::categoryEntityToDTO)
                .collect(Collectors.toList());
    }

    private ProductDTO productEntityToDTO(ProductEntity p) {
        return new ProductDTO(
                p.getId(),
                p.getTitle(),
                p.getDescription(),
                p.getOutPrice(),
                p.getImage(),
                p.getCloseDate(),
                p.getPublishDate(),
                clientEntityToDto(p.getVendedor()),
                categoryEntityToDTO(p.getCategoryId())

        );
    }

    public ProductClientDTO loginDTOtoClientDTO(uma.taw.ubayspring.dto.LoginDTO logindto) {
        LoginCredentialsEntity credentials = loginCredentialsRepository.findLoginCredentialsEntityByUsername(logindto.getUsername());
        if (credentials.getClient() == null) return null;
        return new ProductClientDTO(credentials.getClient().getId(), credentials.getKind());
    }

    public void deleteProduct(int id) throws MinioException {
        ProductEntity p = productRepository.findById(id).get();

        if (p.getImage() != null) {
            minioWrapperService.removeObject(p.getImage());
        }

        productRepository.delete(p);
    }

    public void updateProduct(ProductFormParamsDTO paramsDTO) throws IOException, ServletException {
        CategoryEntity cat = categoryRepository.findById(paramsDTO.getCategory()).get();
        ProductEntity p = productRepository.findById(paramsDTO.getProductId()).get();
        var file = paramsDTO.getImage();


        // IMAGEN
        if (file != null && !file.isEmpty()) {
            InputStream inputStream = file.getInputStream();
            String imgName = null;

            try {
                imgName = minioWrapperService.uploadObject(inputStream);
                if (!imgName.equals(p.getImage())) {
                    minioWrapperService.removeObject(p.getImage());
                }
            } catch (Exception e) {
                throw new ServletException(e.getStackTrace().toString());
            }

            p.setImage(imgName);
        }

        // ESTADO
        if(paramsDTO.getStatus() != null){
            if (paramsDTO.getStatus().equals("Cerrado")) {
                if (p.getCloseDate() == null) {
                    p.setCloseDate(new Date(new java.util.Date().getTime()));
                }
            } else if (paramsDTO.getStatus().equals("Activo")) {
                if (p.getCloseDate() != null) {
                    p.setCloseDate(null);
                }
            }
        }

        p.setTitle(paramsDTO.getTitle());
        p.setDescription(paramsDTO.getDescription());
        p.setCategoryId(cat);
        p.setOutPrice(paramsDTO.getPrice());

        productRepository.save(p);
    }

    private ProductClientDTO clientEntityToDto(ClientEntity client) {
        return new ProductClientDTO(client.getId(), KindEnum.client);
    }

    private ProductCategoryDTO categoryEntityToDTO(CategoryEntity category) {
        return new ProductCategoryDTO(category.getId(), category.getName());
    }

    public ProductBidDTO getHighestBid(int productId) {
        ProductEntity producto = productRepository.findById(productId).get();
        BidEntity highestBid = bidRepositoryCustom.getHighestBidByProduct(producto);

        if (highestBid == null) return null;
        return new ProductBidDTO(highestBid.getAmount());
    }


}
