package fertdt.dto.request;

import fertdt.dto.PredictedTripDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaxiCallRequest {
    @NotNull(message = "Taxi call can not be null")
    private UpcomingTaxiCallRequest taxiCall;

    @NotNull(message = "Predicted trip can not be null")
    private PredictedTripDto predictedTrip;
}
