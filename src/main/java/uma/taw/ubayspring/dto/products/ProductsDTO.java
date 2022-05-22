package uma.taw.ubayspring.dto.products;

import lombok.Value;

import java.util.List;

/**
 * @author Francisco Javier Hernández
 */

@Value
public class ProductsDTO {
    List<ProductDTO> productsList;
    int size;
}
