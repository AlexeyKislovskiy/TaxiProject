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
@ApiModel(description = "Жалоба")
public class ComplaintResponse {
    @ApiModelProperty(value = "Текст жалобы", example = "Водитель остановился далеко от указанной точки посадки")
    private String text;
}
