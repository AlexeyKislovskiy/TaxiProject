package fertdt.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Пара токенов")
public class TokenCoupleDto {
    @ApiModelProperty(value = "Access токен", example = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJST0xFIjpbIlVTRVIiXSwic3ViIjoiZmVydGR0IiwiZXhwIjoxNjUzOTkyMjU2LCJpYXQiOjE2NTM5OTE2NTZ9.q0QNcsfQZM5eAW0GUCVk5UrT-TWrMSDvjSyowAcLwIiTM6d-ClOaWRoDCxa1hiJ1FPoAu1tqPpP2igmYyM75cQ")
    @NotNull(message = "Access token can not be null")
    private String accessToken;

    @ApiModelProperty(value = "Refresh токен", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
    @NotNull(message = "Refresh token can not be null")
    private String refreshToken;
}