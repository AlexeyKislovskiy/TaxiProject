package fertdt.dto.request;

import fertdt.dto.PredictedTripDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Вызов такси")
public class TaxiCallRequest {
    @ApiModelProperty(value = "Параметры заказа")
    @NotNull(message = "Taxi call can not be null")
    private UpcomingTaxiCallRequest taxiCall;

    @ApiModelProperty(value = "Расчет деталей поездки")
    @NotNull(message = "Predicted trip can not be null")
    private PredictedTripDto predictedTrip;
}
