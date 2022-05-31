package fertdt.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Привилегия")
public class PrivilegeResponse {
    @ApiModelProperty(value = "ID привилегии")
    private UUID id;

    @ApiModelProperty(value = "Название привилегии", example = "READ")
    private String privilege;

    @ApiModelProperty(value = "Описание привилегии", example = "Read")
    private String privilegeDescription;
}
