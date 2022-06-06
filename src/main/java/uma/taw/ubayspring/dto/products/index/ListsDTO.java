package uma.taw.ubayspring.dto.products.index;

/**
 * @author Francisco Javier Hernández
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uma.taw.ubayspring.dto.products.ProductCategoryDTO;
import uma.taw.ubayspring.dto.products.ProductDTO;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ListsDTO {
    List<ProductCategoryDTO> categoryList;
    List<ProductDTO> productList;
    List<FavOwnedDTO> favOwnedFilterOptions;
}
