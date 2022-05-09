package fertdt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PredictedTripDto {
    private Integer totalDistance;

    private Integer predictedDriverWaitingTime;

    private Integer predictedTripTime;

    private Integer tripPrice;
}
