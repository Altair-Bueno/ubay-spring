package uma.taw.ubayspring.dto.categories;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author José Luis Bueno Pachón
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddCategoryDTO {
    String name;
    String description;
}

