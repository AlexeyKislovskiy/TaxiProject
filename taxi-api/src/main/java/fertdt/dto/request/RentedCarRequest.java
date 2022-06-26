package fertdt.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Автомобиль для сдачи в аренду")
public class RentedCarRequest extends CarRequest {
    @ApiModelProperty(value = "Ежедневная арендная плата в рублях", example = "1000")
    @NotNull(message = "Daily rental price can not be null")
    @Min(value = 0, message = "Daily rental price can not be less than {value}")
    private Integer dailyRentalPrice;
}
