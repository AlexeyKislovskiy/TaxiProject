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
@ApiModel(description = "Класс автомобиля")
public class CarClassResponse {
    @ApiModelProperty(value = "Название", example = "comfort")
    private String name;

    @ApiModelProperty(value = "Время бесплатного ожидания в минутах", example = "3")
    private Integer freeWaitingMinutes;

    @ApiModelProperty(value = "Стоимость платного ожидания в рублях за минуту после превышения времени бесплатного ожидания", example = "13")
    private Integer paidWaitingPriceForMinute;

    @ApiModelProperty(value = "Минимальная цена за поездку", example = "199")
    private Integer minimumPrice;

    @ApiModelProperty(value = "Цена за 1 минуту", example = "12")
    private Integer pricePerMinute;

    @ApiModelProperty(value = "Цена за 1 километр", example = "12")
    private Integer pricePerKilometer;
}
