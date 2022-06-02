package fertdt.api;

import fertdt.annotation.GlobalApiResponses;
import fertdt.dto.request.RatingRequest;
import fertdt.dto.response.ExceptionExtendedResponse;
import fertdt.dto.response.ExceptionResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/rating")
@GlobalApiResponses
public interface RatingApi {
    @ApiOperation(value = "Поставить оценку водителю/пассажиру", code = 202)
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted. Оценка успешно поставлена"),
            @ApiResponse(code = 400, message = "Bad Request. Запрос содержит невалидные данные", response = ExceptionExtendedResponse.class),
            @ApiResponse(code = 404, message = "Not Found. Возможные проблемы: пользователь с таким ID не найден," +
                    " поездка с таким ID не найдена", response = ExceptionResponse.class),
            @ApiResponse(code = 406, message = "Not Acceptable. Возможные проблемы: попытка поставить оценку до окончания" +
                    " поездки или если поездка была отменена, попытка поставить оценку для поездки без участия данного пользователя",
                    response = ExceptionResponse.class),
            @ApiResponse(code = 409, message = "Conflict. Пользователь уже ставил оценку за данную поездку", response = ExceptionResponse.class)
    }
    )
    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void putRating(@ApiParam(value = "Оценка") @Valid @RequestBody RatingRequest ratingRequest);
}
