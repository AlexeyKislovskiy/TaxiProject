package fertdt.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ApiModel(description = "Статус водителя")
public class DriverStatusResponse {
    @ApiModelProperty(value = "Значение", example = "NOT_AT_WORK")
    private String driverStatus;

    @ApiModelProperty(value = "Описание", example = "Driver has signed a contract, is not at work at the moment")
    private String driverStatusDescription;
}
