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
@ApiModel(description = "Таксопарк")
public class TaxiParkResponse {
    @ApiModelProperty(value = "Название", example = "Taxipark example name")
    private String name;

    @ApiModelProperty(value = "Комиссия таксопарка за поездку в процентах", example = "3")
    private Double commissionPercentage;
}
