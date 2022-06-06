package uma.taw.ubayspring.dto.products.index;

/**
 * @author Francisco Javier Hernández
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavOwnedDTO {
    String value;
    String label;
}
