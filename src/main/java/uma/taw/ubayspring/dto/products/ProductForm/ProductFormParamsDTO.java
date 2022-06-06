package uma.taw.ubayspring.dto.products.ProductForm;

/**
 * @author Francisco Javier Hernández
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import uma.taw.ubayspring.dto.products.ProductClientDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductFormParamsDTO {
    String title;
    String description;
    double price;
    int category;
    MultipartFile image;
    int productId;
    String status;
    ProductClientDTO vendor;
}
