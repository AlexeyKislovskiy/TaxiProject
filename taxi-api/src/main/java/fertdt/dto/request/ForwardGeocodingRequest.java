package fertdt.dto.request;

import fertdt.validation.annotation.WordsNumber;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Прямое геокодирование")
public class ForwardGeocodingRequest {
    @ApiModelProperty(value = "Название места", example = "Russia, Tatarstan, Казань, 420081, Улица Космонавтов 32")
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 256, message = "Name cannot be more than {max} characters")
    @WordsNumber(max = 20, message = "Name must consist of at most {max} words")
    private String name;

    @ApiModelProperty(value = "Максимальное число точек в ответе", example = "5")
    @NotNull(message = "Limit cannot be null")
    @Min(value = 1, message = "Limit cannot be less than {value}")
    @Max(value = 10, message = "Limit cannot be more than {value}")
    private Integer limit;

    @ApiModelProperty(value = "Координаты, относительно которых совершать поиск. Если указаны, в ответе приоритет будет отдаваться точкам, более близким к заданной")
    private GeographicalCoordinatesRequest proximityCoordinates;
}
