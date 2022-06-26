package fertdt.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Категория транспортного средства")
public class VehicleCategoryResponse {
    @ApiModelProperty(value = "Название", example = "B")
    private String name;

    @ApiModelProperty(value = "Описание", example = "Passenger cars (gross weight up to 3.5 tons, passenger seats — no more than 8)")
    private String description;
}
