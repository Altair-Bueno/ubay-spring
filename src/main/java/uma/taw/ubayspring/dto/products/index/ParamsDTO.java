package uma.taw.ubayspring.dto.products.index;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uma.taw.ubayspring.dto.products.ProductClientDTO;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParamsDTO {
    int page;
    int category;
    String name;
    String favOwnedFilter;
}
