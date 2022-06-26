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
@ApiModel(description = "Оценка")
public class RatingResponse {
    @ApiModelProperty(value = "Значение оценки по пятибалльной шкале", example = "5")
    private Integer value;
}
