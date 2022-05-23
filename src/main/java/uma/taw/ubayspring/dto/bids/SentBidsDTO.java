package uma.taw.ubayspring.dto.bids;

import lombok.Value;

import java.sql.Timestamp;
/**
 * @author Altair Bueno
 */

@Value
public class SentBidsDTO {
    Timestamp publishDate;
    double amount;
    ProductDTO product;
}
