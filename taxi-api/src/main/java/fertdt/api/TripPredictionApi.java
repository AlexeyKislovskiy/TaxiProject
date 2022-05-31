package fertdt.api;

import fertdt.dto.PredictedTripDto;
import fertdt.dto.request.UpcomingTaxiCallRequest;
import fertdt.dto.response.ExceptionExtendedResponse;
import fertdt.dto.response.ExceptionResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/trip-prediction")
public interface TripPredictionApi {
    @ApiOperation(value = "Рассчитать детали поездки")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Детали поездки успешно получены", response = PredictedTripDto.class),
            @ApiResponse(code = 400, message = "Bad Request. Запрос содержит невалидные данные", response = ExceptionExtendedResponse.class),
            @ApiResponse(code = 404, message = "Not Found. Класс автомобиля с таким ID не найден", response = ExceptionResponse.class),
            @ApiResponse(code = 409, message = "Conflict. Заказ не может содержать одинаковые пункты в качестве стартового," +
                    " промежуточных или конечного", response = ExceptionResponse.class)
    }
    )
    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    PredictedTripDto predictTrip(@ApiParam(value = "Параметры заказа") @Valid @RequestBody UpcomingTaxiCallRequest taxiCallRequest);


    @ApiOperation(value = "Рассчитать время до прибытия водителя")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Время до прибытия водителя успешно получено", response = Integer.class),
            @ApiResponse(code = 404, message = "Not Found. Пользователь с таким ID не найден", response = ExceptionResponse.class),
            @ApiResponse(code = 406, message = "Not Acceptable. У данного пользователя нет активного заказа со статусом" +
                    " ожидания прибытия водителя", response = ExceptionResponse.class)
    }
    )
    @GetMapping(value = "{user-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    Integer predictTimeToDriverArriving(@ApiParam(value = "ID пользователя", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                                        @PathVariable("user-id") UUID userId);
}
