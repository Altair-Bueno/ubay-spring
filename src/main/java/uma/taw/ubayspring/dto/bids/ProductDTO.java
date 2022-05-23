package uma.taw.ubayspring.dto.bids;

import lombok.Value;
/**
 * @author Altair Bueno
 */

@Value
public class ProductDTO {
    int id;
    String title;
    VendorDTO vendor;
}
