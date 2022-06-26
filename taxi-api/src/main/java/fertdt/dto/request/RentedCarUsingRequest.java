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
@ApiModel(description = "Запрос на использование арендованного автомобиля")
public class RentedCarUsingRequest extends CarUsingRequest {
    @ApiModelProperty(value = "Число дней, на сколько арендуется автомобиль", example = "7")
    @NotNull(message = "Rented for days can not be null")
    @Min(value = 1, message = "Rented for days can not be less than {value}")
    private Integer rentedForDays;
}
