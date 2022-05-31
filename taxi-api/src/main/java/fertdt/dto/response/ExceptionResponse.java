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
@ApiModel(description = "Сообщение об ошибке")
public class ExceptionResponse {
    @ApiModelProperty(value = "Статусный код ошибки", example = "404")
    private Integer status;

    @ApiModelProperty(value = "Название ошибки", example = "Not Found")
    private String error;

    @ApiModelProperty(value = "Сообщение ошибки", example = "Car class not found")
    private String message;
}
