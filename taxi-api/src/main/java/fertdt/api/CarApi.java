package fertdt.api;

import fertdt.annotation.GlobalApiResponses;
import fertdt.dto.request.PersonalCarRequest;
import fertdt.dto.request.RentedCarRequest;
import fertdt.dto.response.CarResponse;
import fertdt.dto.response.ExceptionExtendedResponse;
import fertdt.dto.response.ExceptionResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/cars")
@GlobalApiResponses
public interface CarApi {
    @ApiOperation(value = "Создать личный автомобиль", code = 201)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created. Автомобиль успешно создан", response = UUID.class),
            @ApiResponse(code = 400, message = "Bad Request. Запрос содержит невалидные данные", response = ExceptionExtendedResponse.class),
            @ApiResponse(code = 404, message = "Not Found. Возможные проблемы: класс автомобиля с таким ID не найден, " +
                    "таксопарк с таким ID не найден, владелец с таким ID не найден", response = ExceptionResponse.class),
            @ApiResponse(code = 409, message = "Conflict. Возможные проблемы: автомобиль с таким номером уже существует, " +
                    "таксопарк владельца и автомобиля отличается", response = ExceptionResponse.class)
    }
    )
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_TAXI_PARK_MODERATOR')")
    @PostMapping(value = "/personal", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    UUID createPersonalCar(@ApiParam(value = "Личный автомобиль") @Valid @RequestBody PersonalCarRequest car);


    @ApiOperation(value = "Создать автомобиль для сдачи в аренду", code = 201)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created. Автомобиль успешно создан", response = UUID.class),
            @ApiResponse(code = 400, message = "Bad Request. Запрос содержит невалидные данные", response = ExceptionExtendedResponse.class),
            @ApiResponse(code = 404, message = "Not Found. Возможные проблемы: класс автомобиля с таким ID не найден, " +
                    "таксопарк с таким ID не найден", response = ExceptionResponse.class),
            @ApiResponse(code = 409, message = "Conflict. Автомобиль с таким номером уже существует", response = ExceptionResponse.class)
    }
    )
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_TAXI_PARK_MODERATOR')")
    @PostMapping(value = "/rented", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    UUID createRentedCar(@ApiParam(value = "Автомобиль для сдачи в аренду") @Valid @RequestBody RentedCarRequest car);


    @ApiOperation(value = "Получить информацию об автомобиле по его ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Автомобиль успешно получен", response = CarResponse.class),
            @ApiResponse(code = 404, message = "Not Found. Автомобиль с таким ID не найден", response = ExceptionResponse.class)
    }
    )
    @GetMapping(value = "/{car-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    CarResponse getCar(@ApiParam(value = "ID автомобиля", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                       @PathVariable("car-id") UUID carId);


    @ApiOperation(value = "Удалить автомобиль по его ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Автомобиль успешно удален"),
            @ApiResponse(code = 404, message = "Not Found. Автомобиль с таким ID не найден", response = ExceptionResponse.class)
    }
    )
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_TAXI_PARK_MODERATOR')")
    @DeleteMapping(value = "/{car-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    void deleteCar(@ApiParam(value = "ID автомобиля", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                   @PathVariable("car-id") UUID carId);


    @ApiOperation(value = "Обновить личный автомобиль по его ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Автомобиль успешно обновлен", response = CarResponse.class),
            @ApiResponse(code = 400, message = "Bad Request. Запрос содержит невалидные данные", response = ExceptionExtendedResponse.class),
            @ApiResponse(code = 404, message = "Not Found. Возможные проблемы: автомобиль с таким ID не найден," +
                    " класс автомобиля с таким ID не найден, таксопарк с таким ID не найден, владелец с таким ID не найден",
                    response = ExceptionResponse.class),
            @ApiResponse(code = 409, message = "Conflict. Возможные проблемы: автомобиль с таким номером уже существует, " +
                    "таксопарк владельца и автомобиля отличается", response = ExceptionResponse.class)
    }
    )
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_TAXI_PARK_MODERATOR')")
    @PutMapping(value = "/personal/{car-id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    CarResponse updatePersonalCar(@ApiParam(value = "ID автомобиля", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                                  @PathVariable("car-id") UUID carId, @ApiParam(value = "Обновленные значения личного автомобиля")
                                  @Valid @RequestBody PersonalCarRequest car);


    @ApiOperation(value = "Обновить автомобиль для сдачи в аренду по его ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Автомобиль успешно обновлен", response = CarResponse.class),
            @ApiResponse(code = 400, message = "Bad Request. Запрос содержит невалидные данные", response = ExceptionExtendedResponse.class),
            @ApiResponse(code = 404, message = "Not Found. Возможные проблемы: автомобиль с таким ID не найден," +
                    " класс автомобиля с таким ID не найден, таксопарк с таким ID не найден", response = ExceptionResponse.class),
            @ApiResponse(code = 409, message = "Conflict. Автомобиль с таким номером уже существует", response = ExceptionResponse.class)
    }
    )
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_TAXI_PARK_MODERATOR')")
    @PutMapping(value = "/rented/{car-id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    CarResponse updateRentedCar(@ApiParam(value = "ID автомобиля", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                                @PathVariable("car-id") UUID carId, @ApiParam(value = "Обновленные значения автомобиля для сдачи в аренду")
                                @Valid @RequestBody RentedCarRequest car);
}
