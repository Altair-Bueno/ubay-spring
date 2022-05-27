package uma.taw.ubayspring.dto.products.index;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;
import uma.taw.ubayspring.dto.products.ProductCategoryDTO;
import uma.taw.ubayspring.dto.products.ProductClientDTO;
import uma.taw.ubayspring.dto.products.ProductDTO;
import uma.taw.ubayspring.entity.CategoryEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductIndexDTO {
     ParamsDTO paramDTO;
     ListsDTO listDTO;
}
