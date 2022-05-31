package fertdt.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Доступные для использования автомобили")
public class AvailableCarsResponse {
    @ApiModelProperty(value = "Личные автомобили")
    private Set<CarResponse> personalCars;

    @ApiModelProperty(value = "Автомобили для сдачи в аренду")
    private Set<CarResponse> rentedCars;
}
