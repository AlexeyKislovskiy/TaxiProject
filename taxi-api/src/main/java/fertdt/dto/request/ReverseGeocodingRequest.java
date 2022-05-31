package fertdt.dto.request;

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
@ApiModel(description = "Обратное геокодирование")
public class ReverseGeocodingRequest {
    @ApiModelProperty(value = "Географические координаты")
    @NotNull(message = "Geographical coordinates can not be null")
    private GeographicalCoordinatesRequest geographicalCoordinates;
}
