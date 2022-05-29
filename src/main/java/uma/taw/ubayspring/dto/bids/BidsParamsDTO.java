package uma.taw.ubayspring.dto.bids;

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
    int page;
    String orderBy;
    boolean asc;
}
