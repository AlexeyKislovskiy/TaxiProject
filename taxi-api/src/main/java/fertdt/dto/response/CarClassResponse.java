package fertdt.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarClassResponse {
    private String name;

    private Integer freeWaitingMinutes;

    private Integer paidWaitingPriceForMinute;

    private Integer minimumPrice;

    private Integer pricePerMinute;

    private Integer pricePerKilometer;
}
