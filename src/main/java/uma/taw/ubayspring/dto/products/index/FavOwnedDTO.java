package uma.taw.ubayspring.dto.products.index;

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
