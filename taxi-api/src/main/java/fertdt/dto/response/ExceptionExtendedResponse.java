package fertdt.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Расширенное сообщение об ошибке")
public class ExceptionExtendedResponse extends ExceptionResponse {
    @ApiModelProperty(value = "Список ошибок", dataType = "List", example = "[\"Manufacture year can not be more than 2022\"," +
            " \"Child seats number can not be less than 0\"]")
    private List<String> errors;
}
