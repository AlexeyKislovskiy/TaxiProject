package fertdt.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.Set;
import java.util.UUID;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Пользователь")
public class UserResponse {
    @ApiModelProperty(value = "ID пользователя", example = "fertdt")
    private UUID id;

    @ApiModelProperty(value = "Роли")
    private Set<RoleResponse> roles;

    @ApiModelProperty(value = "Юзернейм", example = "fertdt")
    private String username;

    @ApiModelProperty(value = "Имя", example = "Alexey")
    private String firstName;

    @ApiModelProperty(value = "Фамилия", example = "Kislovskiy")
    private String lastName;

    @ApiModelProperty(value = "Рейтинг в качестве пассажира", example = "4.5")
    private Double ratingAsPassenger;

    @ApiModelProperty(value = "Текущее местоположение")
    private GeographicalPointResponse currentLocation;
}
