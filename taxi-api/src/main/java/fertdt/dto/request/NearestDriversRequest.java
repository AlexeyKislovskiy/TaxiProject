package fertdt.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Ближайшие водители")
public class NearestDriversRequest {
    @ApiModelProperty(value = "ID поездки")
    @NotNull(message = "Taxi ride id can not be null")
    private UUID taxiRideId;

    @ApiModelProperty(value = "Максимальное число водителей", example = "10")
    @NotNull(message = "Limit cannot be null")
    @Min(value = 1, message = "Limit cannot be less than {value}")
    private Integer limit;
}
