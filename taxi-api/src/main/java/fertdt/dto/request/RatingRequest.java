package fertdt.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Оценка")
public class RatingRequest {
    @ApiModelProperty(value = "ID поездки")
    @NotNull(message = "Taxi ride id can not be null")
    private UUID taxiRideId;

    @ApiModelProperty(value = "ID пользователя, ставящего оценку")
    @NotNull(message = "User id can not be null")
    private UUID userId;

    @ApiModelProperty(value = "Значение оценки по пятибалльной шкале", example = "5")
    @NotNull(message = "Rating value can not be null")
    @Min(value = 1, message = "Rating value can not be less than {value}")
    @Max(value = 5, message = "Rating value can not be more than {value}")
    private Integer value;
}
