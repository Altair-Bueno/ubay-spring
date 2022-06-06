package uma.taw.ubayspring.service;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import uma.taw.ubayspring.dto.categories.CategoriesDTO;
import uma.taw.ubayspring.dto.categories.CategoryDTO;
import uma.taw.ubayspring.dto.categories.ModifyCategoryDTO;
import uma.taw.ubayspring.entity.CategoryEntity;
import uma.taw.ubayspring.entity.ClientEntity;
import uma.taw.ubayspring.entity.UserFavouritesEntity;
import uma.taw.ubayspring.exception.UbayException;
import uma.taw.ubayspring.repository.CategoryRepository;
import uma.taw.ubayspring.repository.ClientRepository;
import uma.taw.ubayspring.repository.FavouritesRepositoryCustom;
import uma.taw.ubayspring.repository.UserFavouritesRepository;
import uma.taw.ubayspring.types.KindEnum;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriesService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    UserFavouritesRepository favouritesRepository;

    @Autowired
    FavouritesRepositoryCustom favouritesRepositoryCustom;

    @Autowired
    AuthService authService;


    /**
     * @author: José Luis Bueno Pachón
     */

    public void addCategory(String name, String description) {
        if (name != null && description != null) {
            CategoryEntity category = new CategoryEntity();
            category.setName(name);
            category.setDescription(description);
            categoryRepository.save(category);
        }
    }

    /**
     * @author: José Luis Bueno Pachón
     */

    public void addFavouriteCategory(String clientID, String categoryID) {
        ClientEntity client = clientRepository.findById(Integer.parseInt(clientID)).get();
        CategoryEntity category = categoryRepository.findById(Integer.parseInt(categoryID)).get();

        UserFavouritesEntity favourite = new UserFavouritesEntity();
        favourite.setCategory(category);
        favourite.setClient(client);
        favouritesRepository.save(favourite);
    }

    /**
     * @author: José Luis Bueno Pachón
     */

    @NotNull
    private List<CategoryDTO> userFavouriteCategories(User user) {
        if (user != null) {
            ClientEntity client = authService.getCredentialsEntity(user).getClient();
            List<CategoryEntity> userFavouriteCategories = favouritesRepositoryCustom.getClientFavouriteCategories(client);
            return userFavouriteCategories.stream().map(this::categoryEntityToDTO).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * @author: José Luis Bueno Pachón
     */

    @NotNull
    private List<CategoryDTO> categories() {
        var listaCategorias = (List<CategoryEntity>) categoryRepository.findAll();

        return listaCategorias.stream().map(this::categoryEntityToDTO).collect(Collectors.toList());
        //return categoryRepository.findAll().stream().map(this::categoryEntityToDTO).collect(Collectors.toList());
    }

    /**
     * @author: José Luis Bueno Pachón
     */

    private CategoryDTO categoryEntityToDTO(CategoryEntity category) {
        return new CategoryDTO(category.getId(), category.getName(), category.getDescription());
    }

    /**
     * @author José Luis Bueno Pachón
     */

    private ClientEntity getClientEntity(User user) {
        return authService.getCredentialsEntity(user).getClient();
    }

    /**
     * @author José Luis Bueno Pachón
     */

    public void deleteCategory(String id) throws UbayException {
        if (id != null) {
            try {
                CategoryEntity category = categoryRepository.findById(Integer.parseInt(id)).get();
                categoryRepository.delete(category);
            } catch (Exception e) {
                throw new UbayException("Cannot delete a category that is being used by a product.");
            }
        }
    }

    /**
     * @author José Luis Bueno Pachón
     */

    public void deleteFavourite(String clientID, String categoryID) {
        ClientEntity client = clientRepository.findById(Integer.parseInt(clientID)).get();
        CategoryEntity category = categoryRepository.findById(Integer.parseInt(categoryID)).get();

        UserFavouritesEntity favourite = new UserFavouritesEntity();
        favourite.setCategory(category);
        favourite.setClient(client);
        favouritesRepository.delete(favourite);
    }

    /**
     * @author José Luis Bueno Pachón
     */
    public void modify(CategoryDTO categoryDTO) {
        CategoryEntity category = categoryRepository.findById(categoryDTO.getId()).get();

        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());

        categoryRepository.save(category);
    }

    /**
     * @author José Luis Bueno Pachón
     */
    @NotNull
    public CategoriesDTO processCategories(User user) {
        List<CategoryDTO> userFavouriteCategories = new ArrayList<>();
        List<CategoryDTO> categoryList = categories();
        int userID = -1;

        var loginCredentials = authService.getCredentialsEntity(user);


        if (loginCredentials.getKind().equals(KindEnum.client)) {
            userFavouriteCategories = userFavouriteCategories(user);
            userID = getClientEntity(user).getId();
        }

        return new CategoriesDTO(userFavouriteCategories, categoryList, userID, loginCredentials);
    }
}