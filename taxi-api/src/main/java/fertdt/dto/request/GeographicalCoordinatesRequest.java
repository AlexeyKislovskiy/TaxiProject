package fertdt.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Географические координаты")
public class GeographicalCoordinatesRequest {
    @ApiModelProperty(value = "Долгота", example = "49.19157865")
    @NotNull(message = "Longitude cannot be null")
    @Min(value = -180, message = "Longitude cannot be less than {value}")
    @Max(value = 180, message = "Longitude cannot be more than {value}")
    private Double longitude;

    @ApiModelProperty(value = "Широта", example = "55.79721225")
    @NotNull(message = "Latitude cannot be null")
    @Min(value = -90, message = "Latitude cannot be less than {value}")
    @Max(value = 90, message = "Latitude cannot be more than {value}")
    private Double latitude;
}
