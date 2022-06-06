package uma.taw.ubayspring.dto.bids;

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
public class BidsParamsDTO {
    String startDate;
    String endDate;
    String productTitle;
    String clientName;
    String vendorName;
    int page;
    String orderBy;
    boolean asc;
}
