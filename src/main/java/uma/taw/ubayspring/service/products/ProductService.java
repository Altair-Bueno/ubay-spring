package uma.taw.ubayspring.service.products;

import com.jlefebure.spring.boot.minio.MinioException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uma.taw.ubayspring.dto.products.*;
import uma.taw.ubayspring.entity.*;
import uma.taw.ubayspring.repository.*;
import uma.taw.ubayspring.service.AuthService;
import uma.taw.ubayspring.service.UsersService;
import uma.taw.ubayspring.types.KindEnum;
import uma.taw.ubayspring.wrapper.MinioWrapperService;

import javax.servlet.ServletException;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
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

    //@Autowired // TODO crash
    ProductFavouritesRepositoryCustom productFavouritesRepositoryCustom;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    LoginCredentialsRepository loginCredentialsRepository;

    @Autowired
    MinioWrapperService minioWrapperService;

    @Autowired
    AuthService authService;


    public ProductsDTO getProductsList(ProductClientDTO sesionClient, String productName, String category, String favOwnedFilter, String page) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        ProductRepositoryCustomImpl.ProductTupleResult<ProductEntity> ptr;
        CategoryEntity cat = null;
        ClientEntity clientEntity = null;
        String name = null;
        boolean favFilter = false, ownedFilter = false;
        int pageParam = Integer.parseInt(page) - 1;

        // Set fav / owned filters
        if(favOwnedFilter.equals("favFilter")){
            favFilter = true;
        } else if(favOwnedFilter.equals("ownedFilter")){
            ownedFilter = true;
        }

        // Set category
        if (!category.equals("0")) {
            int catId = Integer.parseInt(category);
            cat = categoryRepository.findById(catId).get();
        }

        // Set client
        if(sesionClient != null) {
            clientEntity = clientRepository.findById(sesionClient.getId()).get();
        }

        // Set name
        if(!productName.equals("")){
            name = productName;
        }

        // Filters:
        if(favFilter){
            ptr = productFavouritesRepositoryCustom.getClientFavouriteProductsFiltered(clientEntity, name, cat, pageParam);
        } else {
            ptr = productRepositoryCustom.filterAndGetByPage(clientEntity, name, cat, ownedFilter, pageParam);
        }

        for (ProductEntity p : ptr.getProductEntities()) {
            productDTOS.add(
                    productEntityToDTO(p)
            );
        }

        return new ProductsDTO(productDTOS, ptr.getActualSize());
    }

    @NonNull
    public boolean isProductUserFavourite(ProductClientDTO client, int id) {
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

    public ProductDTO createProduct(String title, String description, double outPrice, String image, java.util.Date publishDate, int vendedorId, int categoryId) {
        ProductEntity p = new ProductEntity();
        ClientEntity vendedorEntity = clientRepository.findById(vendedorId).get();
        CategoryEntity categoryEntity = categoryRepository.findById(categoryId).get();

        p.setTitle(title);
        p.setDescription(description);
        p.setOutPrice(outPrice);
        p.setImage(image);
        p.setCloseDate(null);
        p.setPublishDate(new Timestamp(publishDate.getTime()));
        p.setVendedor(vendedorEntity);
        p.setCategoryId(categoryEntity);

        productRepository.save(p);
        p = productRepository.findById(p.getId()).get();

        return productEntityToDTO(p);
    }

    public ProductDTO findByIdProduct(int id) {
        return productEntityToDTO(productRepository.findById(id).get());
    }

    @NonNull
    public List<ProductCategoryDTO> categories() {
        return StreamSupport
                .stream(categoryRepository.findAll(Sort.by(Sort.Direction.DESC, "id")).spliterator(), false)
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

    public void updateProduct(int producto, int categoria, String estado, String desc, String titulo, Double precio, Part file) throws IOException, ServletException {
        CategoryEntity cat = categoryRepository.findById(categoria).get();
        ProductEntity p = productRepository.findById(producto).get();


        // IMAGEN
        if (file != null && !file.getSubmittedFileName().equals("")) {
            InputStream inputStream = file.getInputStream();
            String img = "";

            try {
                img = minioWrapperService.uploadObject(inputStream);
                if (!img.equals(p.getImage())) {
                    minioWrapperService.removeObject(p.getImage());
                }
            } catch (Exception e) {
                throw new ServletException(e.getStackTrace().toString());
            }

            p.setImage(img);
        }

        // ESTADO
        if(estado != null){
            if (estado.equals("Cerrado")) {
                if (p.getCloseDate() == null) {
                    p.setCloseDate(new Date(new java.util.Date().getTime()));
                }
            } else if (estado.equals("Activo")) {
                if (p.getCloseDate() != null) {
                    p.setCloseDate(null);
                }
            }
        }

        p.setTitle(titulo);
        p.setDescription(desc);
        p.setCategoryId(cat);
        p.setOutPrice(precio);

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
