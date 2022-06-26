package fertdt.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Юзернейм и пароль пользователя")
public class UserRequest {
    @ApiModelProperty(value = "Юзернейм", example = "fertdt")
    @NotBlank(message = "Username can not be blank")
    private String username;

    @ApiModelProperty(value = "Пароль", example = "qwerty55")
    @NotBlank(message = "Password can not be blank")
    @Size(min = 8, message = "Password cannot be less than {min} characters")
    private String password;
}
