package fertdt.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Пара токенов")
public class TokenCoupleResponse {
    @ApiModelProperty(value = "Access токен", example = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJST0xFIjpbIlVTRVIiXSwic3ViIjoiZmVydGR0IiwiZXhwIjoxNjUzOTkyMjU2LCJpYXQiOjE2NTM5OTE2NTZ9.q0QNcsfQZM5eAW0GUCVk5UrT-TWrMSDvjSyowAcLwIiTM6d-ClOaWRoDCxa1hiJ1FPoAu1tqPpP2igmYyM75cQ")
    private String accessToken;

    @ApiModelProperty(value = "Refresh токен", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    private String refreshToken;

    @ApiModelProperty(value = "Срок истечения access токена", example = "1653992256000")
    private Date accessTokenExpirationDate;
}
