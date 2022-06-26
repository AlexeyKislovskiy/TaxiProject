package fertdt.dto.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Пользователь")
public class UserExtendedRequest extends UserRequest {
    @ApiModelProperty(value = "Имя", example = "Alexey")
    @NotBlank(message = "First name can not be blank")
    private String firstName;

    @ApiModelProperty(value = "Фамилия", example = "Kislovskiy")
    @NotBlank(message = "Last name can not be blank")
    private String lastName;
}

