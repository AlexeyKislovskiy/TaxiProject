package fertdt.api;

import fertdt.dto.request.TaxiCallRequest;
import fertdt.dto.response.ExceptionExtendedResponse;
import fertdt.dto.response.ExceptionResponse;
import fertdt.dto.response.TaxiRideResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/taxi-call")
public interface TaxiRideApi {
    @ApiOperation(value = "Вызвать такси", code = 202)
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted. Такси успешно вызвано", response = UUID.class),
            @ApiResponse(code = 400, message = "Bad Request. Запрос содержит невалидные данные", response = ExceptionExtendedResponse.class),
            @ApiResponse(code = 404, message = "Not Found. Возможные проблемы: пользователь с таким ID не найден, " +
                    "класс автомобиля с таким ID не найден, способ платежа с таким ID не найден", response = ExceptionResponse.class),
            @ApiResponse(code = 406, message = "Not Acceptable. Данный пользователь находится на работе в качестве водителя", response = ExceptionResponse.class),
            @ApiResponse(code = 409, message = "Conflict. Возможные проблемы: у пользователя уже есть активный заказ, " +
                    "заказ не может содержать одинаковые пункты в качестве стартового, промежуточных или конечного",
                    response = ExceptionResponse.class)
    }
    )
    @PostMapping(value = "/call", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    UUID callTaxi(@ApiParam(value = "Вызов такси") @Valid @RequestBody TaxiCallRequest taxiCallRequest);


    @ApiOperation(value = "Отменить вызов такси", code = 202)
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted. Вызов такси успешно отменен"),
            @ApiResponse(code = 404, message = "Not Found. Пользователь с таким ID не найден", response = ExceptionResponse.class),
            @ApiResponse(code = 406, message = "Not Acceptable. Возможные проблемы: у пользователя нет активного заказа, " +
                    "попытка отменить заказ во время поездки или после завершения", response = ExceptionResponse.class)
    }
    )
    @DeleteMapping(value = "call/{user-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void cancelTaxiCall(@ApiParam(value = "ID пользователя", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                        @PathVariable("user-id") UUID userId);


    @ApiOperation(value = "Получить информацию о поездке по ее ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Информация о поездке успешно получена", response = TaxiRideResponse.class),
            @ApiResponse(code = 404, message = "Not Found. Поездка с таким ID не найдена", response = ExceptionResponse.class)
    }
    )
    @GetMapping(value = "/{taxi-ride-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    TaxiRideResponse getTaxiRide(@ApiParam(value = "ID поездки", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                                 @PathVariable("taxi-ride-id") UUID taxiRideId);


    @ApiOperation(value = "Взять заказ", code = 202)
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted. Заказ успешно взят"),
            @ApiResponse(code = 404, message = "Not Found. Возможные проблемы: водитель с таким ID не найден, " +
                    "поездка с таким ID не найдена", response = ExceptionResponse.class),
            @ApiResponse(code = 406, message = "Not Acceptable. Возможные проблемы: данный заказ не находится в статусе " +
                    "поиска водителя, данный водитель не на работе, водитель не использует никакую машину, " +
                    "машина водителя не подходит для данного заказа", response = ExceptionResponse.class),
            @ApiResponse(code = 409, message = "Conflict. У водителя уже есть активный заказ", response = ExceptionResponse.class)
    }
    )
    @PostMapping(value = "/take/{driver-id}/{taxi-ride-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void takeOrder(@ApiParam(value = "ID водителя", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                   @PathVariable("driver-id") UUID driverId,
                   @ApiParam(value = "ID поездки", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                   @PathVariable("taxi-ride-id") UUID taxiRideId);


    @ApiOperation(value = "Прибыть к месту вызова", code = 202)
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted. Успешно отмечено прибытие к пассажиру"),
            @ApiResponse(code = 404, message = "Not Found. Водитель с таким ID не найден", response = ExceptionResponse.class),
            @ApiResponse(code = 406, message = "Not Acceptable. Данный заказ не находится в статусе ожидания прибытия водителя",
                    response = ExceptionResponse.class),
            @ApiResponse(code = 409, message = "Conflict. У водителя нет активной поездки", response = ExceptionResponse.class)
    }
    )
    @PostMapping(value = "/arrive-to-client/{driver-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void arriveToClient(@ApiParam(value = "ID водителя", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                        @PathVariable("driver-id") UUID driverId);


    @ApiOperation(value = "Начать поездку", code = 202)
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted. Поездка успешно начата"),
            @ApiResponse(code = 404, message = "Not Found. Водитель с таким ID не найден", response = ExceptionResponse.class),
            @ApiResponse(code = 406, message = "Not Acceptable. Данный заказ не находится в статусе ожидания прибытия пассажира",
                    response = ExceptionResponse.class),
            @ApiResponse(code = 409, message = "Conflict. У водителя нет активной поездки", response = ExceptionResponse.class)
    }
    )
    @PostMapping(value = "/start/{driver-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void startTrip(@ApiParam(value = "ID водителя", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                   @PathVariable("driver-id") UUID driverId);


    @ApiOperation(value = "Отменить поездку", code = 202)
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted. Поездка успешно отменена"),
            @ApiResponse(code = 404, message = "Not Found. Водитель с таким ID не найден", response = ExceptionResponse.class),
            @ApiResponse(code = 406, message = "Not Acceptable. Попытка отменить поездку до приезда к пассажиру," +
                    " во время поездки или после ее завершения",
                    response = ExceptionResponse.class),
            @ApiResponse(code = 409, message = "Conflict. У водителя нет активной поездки", response = ExceptionResponse.class)
    }
    )
    @DeleteMapping(value = "/{driver-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void cancelTrip(@ApiParam(value = "ID водителя", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                    @PathVariable("driver-id") UUID driverId);


    @ApiOperation(value = "Закончить поездку", code = 202)
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted. Поездка успешно закончена"),
            @ApiResponse(code = 404, message = "Not Found. Водитель с таким ID не найден", response = ExceptionResponse.class),
            @ApiResponse(code = 406, message = "Not Acceptable. Данный заказ не находится в статусе в пути",
                    response = ExceptionResponse.class),
            @ApiResponse(code = 409, message = "Conflict. У водителя нет активной поездки", response = ExceptionResponse.class)
    }
    )
    @PostMapping(value = "/finish/{driver-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void finishTrip(@ApiParam(value = "ID водителя", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                    @PathVariable("driver-id") UUID driverId);
}
