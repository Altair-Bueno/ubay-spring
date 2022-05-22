package uma.taw.ubayspring.dto.categories;

import lombok.Value;
import uma.taw.ubayspring.entity.LoginCredentialsEntity;

import java.util.List;

@Value
public class CategoriesDTO {
    List<CategoryDTO> userFavouriteCategories;
    List<CategoryDTO> categoryList;
    int userID;
    LoginCredentialsEntity login;
}
