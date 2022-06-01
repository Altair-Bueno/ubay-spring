package uma.taw.ubayspring.dto.bids;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BidsSortingOptions {
    String value;
    String label;
}
