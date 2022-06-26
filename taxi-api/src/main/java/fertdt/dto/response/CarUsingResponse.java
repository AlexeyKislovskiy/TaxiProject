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
@ApiModel(description = "Использование автомобиля")
public class CarUsingResponse {
    @ApiModelProperty(value = "Автомобиль")
    private CarResponse car;

    @ApiModelProperty(value = "Водитель")
    private DriverResponse driver;

    @ApiModelProperty(value = "Дата начала использования", example = "25.05.2022")
    private String fromDate;

    @ApiModelProperty(value = "Дата окончания использования. Если автомобиль еще используется, дата максимально" +
            " возможного использования для арендованного автомобиля или null для личного", example = "31.05.2022")
    private String toDate;

    @ApiModelProperty(value = "Плата за использование автомобиля")
    private PaymentForCarUsingResponse payment;
}
