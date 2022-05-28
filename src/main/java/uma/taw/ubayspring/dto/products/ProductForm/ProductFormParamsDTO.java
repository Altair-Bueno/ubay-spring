package uma.taw.ubayspring.dto.products.ProductForm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

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
}
