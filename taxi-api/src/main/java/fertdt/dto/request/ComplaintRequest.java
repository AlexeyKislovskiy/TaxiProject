package fertdt.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Жалоба")
public class ComplaintRequest {
    @ApiModelProperty(value = "ID поездки")
    @NotNull(message = "Taxi ride id can not be null")
    private UUID taxiRideId;

    @ApiModelProperty(value = "ID пользователя, отправляющего жалобу")
    @NotNull(message = "User id can not be null")
    private UUID userId;

    @ApiModelProperty(value = "Текст жалобы", example = "Водитель остановился далеко от указанной точки посадки")
    @NotBlank(message = "Complaint text can not be blank")
    private String text;
}
