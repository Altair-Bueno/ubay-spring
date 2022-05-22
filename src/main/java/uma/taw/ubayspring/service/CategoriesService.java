package uma.taw.ubayspring.service;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import uma.taw.ubayspring.dto.categories.CategoriesDTO;
import uma.taw.ubayspring.dto.categories.CategoryDTO;
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

    public void addCategory(String name, String description) {
        if (name != null && description != null) {
            CategoryEntity category = new CategoryEntity();
            category.setName(name);
            category.setDescription(description);
            categoryRepository.save(category);
        }
    }

    public void addFavouriteCategory(String clientID, String categoryID) {
        ClientEntity client = clientRepository.findById(Integer.parseInt(clientID)).get();
        CategoryEntity category = categoryRepository.findById(Integer.parseInt(categoryID)).get();

        UserFavouritesEntity favourite = new UserFavouritesEntity();
        favourite.setCategory(category);
        favourite.setClient(client);
        favouritesRepository.save(favourite);
    }

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

    @NotNull
    private List<CategoryDTO> categories() {
        var listaCategorias = (List<CategoryEntity>) categoryRepository.findAll();

        return listaCategorias.stream().map(this::categoryEntityToDTO).collect(Collectors.toList());
        //return categoryRepository.findAll().stream().map(this::categoryEntityToDTO).collect(Collectors.toList());
    }

    private CategoryDTO categoryEntityToDTO(CategoryEntity category) {
        return new CategoryDTO(category.getId(), category.getName(), category.getDescription());
    }

    private ClientEntity getClientEntity(User user) {
        return authService.getCredentialsEntity(user).getClient();
    }

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

    public void deleteFavourite(String clientID, String categoryID) {
        ClientEntity client = clientRepository.findById(Integer.parseInt(clientID)).get();
        CategoryEntity category = categoryRepository.findById(Integer.parseInt(categoryID)).get();

        UserFavouritesEntity favourite = new UserFavouritesEntity();
        favourite.setCategory(category);
        favourite.setClient(client);
        favouritesRepository.delete(favourite);
    }

    public void modify(String id, String name, String description) {
        CategoryEntity category = categoryRepository.findById(Integer.parseInt(id)).get();

        category.setName(name);
        category.setDescription(description);

        categoryRepository.save(category);
    }

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