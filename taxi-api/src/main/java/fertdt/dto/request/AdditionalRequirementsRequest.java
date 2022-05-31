package fertdt.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Дополнительные требования к заказу такси")
public class AdditionalRequirementsRequest {
    @ApiModelProperty(value = "Необходимое число пассажирских мест. Водители с недостаточным числом мест не увидят вызов", example = "4")
    @Min(value = 1, message = "Seats number can not be less than {value}")
    private Integer seatsNumber;

    @ApiModelProperty(value = "Необходимое число детских кресел. Водители с недостаточным числом детских кресел не увидят вызов", example = "1")
    @Min(value = 0, message = "Child seats number can not be less than {value}")
    private Integer childSeatsNumber;

    @ApiModelProperty(value = "Дополнительный комментарий водителю", example = "С собой 2 больших сумки, нужна машина с вместительным багажником")
    private String commentToDriver;
}
