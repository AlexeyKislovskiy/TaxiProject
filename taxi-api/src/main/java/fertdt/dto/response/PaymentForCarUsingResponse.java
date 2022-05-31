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
@ApiModel(description = "Плата за использование автомобиля")
public class PaymentForCarUsingResponse {
    @ApiModelProperty(value = "Число дней, на сколько арендован автомобиль", example = "7")
    private Integer rentedForDays;

    @ApiModelProperty(value = "Суммарная цена за все дни аренды", example = "7000")
    private Integer cost;

    @ApiModelProperty(value = "Статус оплаты", example = "NOT_PAID")
    private String paymentStatus;
}
