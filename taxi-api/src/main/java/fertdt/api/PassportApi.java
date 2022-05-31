package fertdt.api;

import fertdt.dto.request.PassportRequest;
import fertdt.dto.response.ExceptionExtendedResponse;
import fertdt.dto.response.ExceptionResponse;
import fertdt.dto.response.PassportResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/passports")
public interface PassportApi {
    @ApiOperation(value = "Добавить паспорт", code = 201)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created. Паспорт успешно добавлен", response = UUID.class),
            @ApiResponse(code = 400, message = "Bad Request. Запрос содержит невалидные данные", response = ExceptionExtendedResponse.class),
            @ApiResponse(code = 404, message = "Not Found. Водитель с таким ID не найден", response = ExceptionResponse.class),
            @ApiResponse(code = 409, message = "Conflict. Паспорт с такими серией и номером уже существует", response = ExceptionResponse.class)
    }
    )
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    UUID addPassport(@ApiParam(value = "Паспорт") @Valid @RequestBody PassportRequest passport);


    @ApiOperation(value = "Получить информацию о паспорте по его ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Паспорт успешно получен", response = PassportResponse.class),
            @ApiResponse(code = 404, message = "Not Found. Паспорт с таким ID не найден", response = ExceptionResponse.class)
    }
    )
    @GetMapping(value = "/{passport-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    PassportResponse getPassport(@ApiParam(value = "ID паспорта", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                                 @PathVariable("passport-id") UUID passportId);


    @ApiOperation(value = "Удалить паспорт по его ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Паспорт успешно удален"),
            @ApiResponse(code = 404, message = "Not Found. Паспорт с таким ID не найден", response = ExceptionResponse.class)
    }
    )
    @DeleteMapping(value = "/{passport-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    void deletePassport(@ApiParam(value = "ID паспорта", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                        @PathVariable("passport-id") UUID passportId);
}
