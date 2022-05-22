package uma.taw.ubayspring.dto.products;

import lombok.Value;

/**
 * @author Francisco Javier Hernández
 */

@Value
public class ProductFavouritesDTO {
    ProductDTO product;
    ProductClientDTO user;
}
