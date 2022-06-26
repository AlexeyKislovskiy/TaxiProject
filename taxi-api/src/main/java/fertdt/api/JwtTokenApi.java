package fertdt.api;

import fertdt.dto.TokenCoupleDto;
import fertdt.dto.response.ExceptionResponse;
import fertdt.dto.response.TokenCoupleResponse;
import fertdt.dto.response.UserResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/token")
public interface JwtTokenApi {
    @ApiOperation(value = "Получить информацию о пользователе по токену")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Пользователь успешно получен", response = UserResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized. Возможные проблемы: пользователь, соответствующий данному " +
                    "токену, не найден, срок действия токена истек, токен некорректный", response = ExceptionResponse.class)
    }
    )
    @GetMapping(value = "/user-info", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    UserResponse userInfoByToken(@ApiParam(value = "Токен", example = "eyJhbGciOiJIUzUxMiJ9.eyJST0xFIjpbIlVTRVIiXSwic3ViIjoiZmVydGR0IiwiZXhwIjoxNjUzOTkyMjU2LCJpYXQiOjE2NTM5OTE2NTZ9.q0QNcsfQZM5eAW0GUCVk5UrT-TWrMSDvjSyowAcLwIiTM6d-ClOaWRoDCxa1hiJ1FPoAu1tqPpP2igmYyM75cQ")
                                 @RequestParam String token);


    @ApiOperation(value = "Обновить пару токенов")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Новая пара токенов успешно получена", response = TokenCoupleResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized. Возможные проблемы: срок действия refresh токена истек," +
                    " данный refresh токен не найден, access токен некорректный", response = ExceptionResponse.class)
    }
    )
    @PostMapping(value = "/refresh", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    TokenCoupleResponse updateTokens(@ApiParam(value = "Пара токенов") @Valid @RequestBody TokenCoupleDto tokenCoupleDto);
}
