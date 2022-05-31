package fertdt.api;

import fertdt.dto.request.GeographicalCoordinatesRequest;
import fertdt.dto.request.UserExtendedRequest;
import fertdt.dto.request.UserRequest;
import fertdt.dto.response.ExceptionExtendedResponse;
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
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/users")
public interface UserApi {
    @ApiOperation(value = "Создать аккаунт нового пользователя", code = 201)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created. Аккаунт успешно создан", response = UUID.class),
            @ApiResponse(code = 400, message = "Bad Request. Запрос содержит невалидные данные", response = ExceptionExtendedResponse.class),
            @ApiResponse(code = 409, message = "Conflict. Пользователь с таким юзернеймом уже существует", response = ExceptionResponse.class)
    }
    )
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    UUID createUser(@ApiParam(value = "Пользователь") @Valid @RequestBody UserExtendedRequest user);


    @ApiOperation(value = "Получить информацию о пользователе по его ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Пользователь успешно получен", response = UserResponse.class),
            @ApiResponse(code = 404, message = "Not Found. Пользователь с таким ID не найден", response = ExceptionResponse.class)
    }
    )
    @GetMapping(value = "/{user-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    UserResponse getUser(@ApiParam(value = "ID пользователя", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                         @PathVariable("user-id") UUID userId);


    @ApiOperation(value = "Удалить пользователя по его ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Пользователь успешно удален"),
            @ApiResponse(code = 404, message = "Not Found. Пользователь с таким ID не найден", response = ExceptionResponse.class)
    }
    )
    @DeleteMapping(value = "/{user-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    void deleteUser(@ApiParam(value = "ID пользователя", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                    @PathVariable("user-id") UUID userId);


    @ApiOperation(value = "Обновить пользователя по его ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Пользователь успешно обновлен", response = UserResponse.class),
            @ApiResponse(code = 400, message = "Bad Request. Запрос содержит невалидные данные", response = ExceptionExtendedResponse.class),
            @ApiResponse(code = 404, message = "Not Found. Пользователь с таким ID не найден", response = ExceptionResponse.class),
            @ApiResponse(code = 409, message = "Conflict. Пользователь с таким юзернеймом уже существует", response = ExceptionResponse.class)
    }
    )
    @PutMapping(value = "/{user-id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    UserResponse updateUser(@ApiParam(value = "ID пользователя", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                            @PathVariable("user-id") UUID userId, @ApiParam(value = "Обновленные значения пользователя")
                            @Valid @RequestBody UserExtendedRequest user);


    @ApiOperation(value = "Войти в аккаунт пользователя")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Пользователь успешно вошел в аккаунт", response = TokenCoupleResponse.class),
            @ApiResponse(code = 400, message = "Bad Request. Запрос содержит невалидные данные", response = ExceptionExtendedResponse.class),
            @ApiResponse(code = 401, message = "Unauthorized. Неудачная попытка войти, пользователя с таким юзернеймом" +
                    " не существует или пароль не правильный", response = ExceptionResponse.class),
    }
    )
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    TokenCoupleResponse login(@ApiParam(value = "Юзернейм и пароль пользователя") @Valid @RequestBody UserRequest userRequest);


    @ApiOperation(value = "Обновить текущее местоположение пользователя", code = 202)
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted. Местоположение пользователя успешно обновлено"),
            @ApiResponse(code = 400, message = "Bad Request. Запрос содержит невалидные данные", response = ExceptionExtendedResponse.class),
            @ApiResponse(code = 404, message = "Not Found. Пользователь с таким ID не найден", response = ExceptionResponse.class),
    }
    )
    @PutMapping(value = "/location/{user-id}", produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void updateCurrentLocation(@ApiParam(value = "ID пользователя", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                               @PathVariable("user-id") UUID userId, @ApiParam(value = "Текущее местоположение пользователя")
                               @Valid @RequestBody GeographicalCoordinatesRequest geographicalCoordinatesRequest);
}
