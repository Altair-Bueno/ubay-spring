package uma.taw.ubayspring.dto.categories;

import lombok.Value;
import uma.taw.ubayspring.entity.LoginCredentialsEntity;

import java.util.List;

/**
 * @author: José Luis Bueno Pachón
 */

@Value
public class CategoriesDTO {
    List<CategoryDTO> userFavouriteCategories;
    List<CategoryDTO> categoryList;
    int userID;
    LoginCredentialsEntity login;
}
