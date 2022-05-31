package fertdt.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Роль")
public class RoleResponse {
    @ApiModelProperty(value = "ID роли")
    private UUID id;

    @ApiModelProperty(value = "Название роли", example = "USER")
    private String role;

    @ApiModelProperty(value = "Описание роли", example = "User")
    private String roleDescription;

    @ApiModelProperty(value = "Привилегии")
    private List<PrivilegeResponse> privileges;
}
