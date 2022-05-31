package fertdt.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Запрос на использование автомобиля")
public class CarUsingRequest {
    @ApiModelProperty(value = "ID водителя")
    @NotNull(message = "Driver id can not be null")
    private UUID driverId;

    @ApiModelProperty(value = "ID автомобиля")
    @NotNull(message = "Car id can not be null")
    private UUID carId;
}
