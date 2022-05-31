package fertdt.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Географическая точка")
public class GeographicalPointResponse {
    @ApiModelProperty(value = "Долгота", example = "49.19157865")
    private Double longitude;

    @ApiModelProperty(value = "Широта", example = "55.79721225")
    private Double latitude;

    @ApiModelProperty(value = "Название пункта", example = "Russia, Tatarstan, Казань, 420081, Улица Космонавтов 32")
    private String name;
}
