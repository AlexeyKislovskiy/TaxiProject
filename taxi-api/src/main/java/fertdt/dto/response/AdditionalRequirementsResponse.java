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
@ApiModel(description = "Дополнительные требования к заказу такси")
public class AdditionalRequirementsResponse {
    @ApiModelProperty(value = "Необходимое число пассажирских мест. Водители с недостаточным числом мест не увидят вызов", example = "4")
    private Integer seatsNumber;

    @ApiModelProperty(value = "Необходимое число детских кресел. Водители с недостаточным числом детских кресел не увидят вызов", example = "1")
    private Integer childSeatsNumber;

    @ApiModelProperty(value = "Дополнительный комментарий водителю", example = "С собой 2 больших сумки, нужна машина с вместительным багажником")
    private String commentToDriver;
}
