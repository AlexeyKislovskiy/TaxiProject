package fertdt.api;

import fertdt.dto.request.CarUsingRequest;
import fertdt.dto.request.RentedCarUsingRequest;
import fertdt.dto.response.AvailableCarsResponse;
import fertdt.dto.response.CarUsingResponse;
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

@RequestMapping("/api/car-using")
public interface CarUsingApi {
    @ApiOperation(value = "Получить информацию об использовании автомобиля по ID данной сущности")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Информация об использовании автомобиля успешно получена", response = CarUsingResponse.class),
            @ApiResponse(code = 404, message = "Not Found. Сущность 'использование автомобиля' с таким ID не найдена", response = ExceptionResponse.class)
    }
    )
    @GetMapping(value = "/{car-using-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    CarUsingResponse getCarUsing(@ApiParam(value = "ID сущности 'использование автомобиля'", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                                 @PathVariable("car-using-id") UUID carUsingId);


    @ApiOperation(value = "Получить информацию о всех доступных автомобилях для данного водителя")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Информация о всех доступных автомобилях успешно получена", response = AvailableCarsResponse.class),
            @ApiResponse(code = 404, message = "Not Found. Водитель с таким ID не найден", response = ExceptionResponse.class),
            @ApiResponse(code = 406, message = "Not Acceptable. Возможные проблемы: аккаунт водителя не верифицирован," +
                    " у водителя нет контракта с таксопарком", response = ExceptionResponse.class)
    }
    )
    @GetMapping(value = "/work/{driver-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    AvailableCarsResponse getAvailableCars(@ApiParam(value = "ID водителя", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                                           @PathVariable("driver-id") UUID driverId);


    @ApiOperation(value = "Использовать личный автомобиль", code = 202)
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted. Автомобиль успешно используется", response = UUID.class),
            @ApiResponse(code = 400, message = "Bad Request. Запрос содержит невалидные данные", response = ExceptionExtendedResponse.class),
            @ApiResponse(code = 404, message = "Not Found. Возможные проблемы: водитель с таким ID не найден," +
                    " автомобиль с таким ID не найден", response = ExceptionResponse.class),
            @ApiResponse(code = 406, message = "Not Acceptable. Возможные проблемы: аккаунт водителя не верифицирован," +
                    " у водителя нет контракта с таксопарком, водитель пытается использовать личный автомобиль другого водителя",
                    response = ExceptionResponse.class),
            @ApiResponse(code = 409, message = "Conflict. Возможные проблемы: автомобиль уже используется, " +
                    "водитель уже использует автомобиль", response = ExceptionResponse.class)
    }
    )
    @PostMapping(value = "work/personal", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    UUID usePersonalCar(@ApiParam(value = "Запрос на использование автомобиля") @Valid @RequestBody CarUsingRequest carUsingRequest);


    @ApiOperation(value = "Использовать арендованный автомобиль", code = 202)
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted. Автомобиль успешно используется", response = UUID.class),
            @ApiResponse(code = 400, message = "Bad Request. Запрос содержит невалидные данные", response = ExceptionExtendedResponse.class),
            @ApiResponse(code = 404, message = "Not Found. Возможные проблемы: водитель с таким ID не найден," +
                    " автомобиль с таким ID не найден", response = ExceptionResponse.class),
            @ApiResponse(code = 406, message = "Not Acceptable. Возможные проблемы: аккаунт водителя не верифицирован," +
                    " у водителя нет контракта с таксопарком, водитель пытается использовать личный автомобиль другого водителя",
                    response = ExceptionResponse.class),
            @ApiResponse(code = 409, message = "Conflict. Возможные проблемы: автомобиль уже используется, " +
                    "водитель уже использует автомобиль", response = ExceptionResponse.class)
    }
    )
    @PostMapping(value = "work/rented", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    UUID useRentedCar(@ApiParam(value = "Запрос на использование арендованного автомобиля")
                      @Valid @RequestBody RentedCarUsingRequest carUsingRequest);


    @ApiOperation(value = "Прекратить использование текущего автомобиля", code = 202)
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted. Использование автомобиля успешно прекращено"),
            @ApiResponse(code = 404, message = "Not Found. Водитель с таким ID не найден", response = ExceptionResponse.class),
            @ApiResponse(code = 406, message = "Not Acceptable. Возможные проблемы: аккаунт водителя не верифицирован," +
                    " у водителя нет контракта с таксопарком, водитель пытается прекратить использование автомобиля во время работы," +
                    " водитель и так не использует автомобиль",
                    response = ExceptionResponse.class)
    }
    )
    @DeleteMapping(value = "work/{driver-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void stopUsingCar(@ApiParam(value = "ID водителя", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                      @PathVariable("driver-id") UUID driverId);
}
