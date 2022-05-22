package uma.taw.ubayspring.dto.products;

import lombok.Value;
import uma.taw.ubayspring.types.KindEnum;

/**
 * @author Francisco Javier Hernández
 */

@Value
public class ProductClientDTO {
    int id;
    KindEnum kind;
}
