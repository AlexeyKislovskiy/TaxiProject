package fertdt.api;

import fertdt.dto.request.TaxiParkRequest;
import fertdt.dto.response.ExceptionExtendedResponse;
import fertdt.dto.response.ExceptionResponse;
import fertdt.dto.response.TaxiParkResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/taxi-parks")
public interface TaxiParkApi {
    @ApiOperation(value = "Создать таксопарк", code = 201)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created. Таксопарк успешно создан", response = UUID.class),
            @ApiResponse(code = 400, message = "Bad Request. Запрос содержит невалидные данные", response = ExceptionExtendedResponse.class),
            @ApiResponse(code = 409, message = "Conflict. Таксопарк с таким названием уже существует", response = ExceptionResponse.class)
    }
    )
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    UUID createTaxiPark(@ApiParam(value = "Таксопарк") @Valid @RequestBody TaxiParkRequest taxiPark);


    @ApiOperation(value = "Получить информацию о таксопарке по его ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Таксопарк успешно получен", response = TaxiParkResponse.class),
            @ApiResponse(code = 404, message = "Not Found. Таксопарк с таким ID не найден", response = ExceptionResponse.class)
    }
    )
    @GetMapping(value = "/{taxi-park-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    TaxiParkResponse getTaxiPark(@ApiParam(value = "ID таксопарка", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                                 @PathVariable("taxi-park-id") UUID taxiParkId);


    @ApiOperation(value = "Удалить таксопарк по его ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Таксопарк успешно удален"),
            @ApiResponse(code = 404, message = "Not Found. Таксопарк с таким ID не найден", response = ExceptionResponse.class)
    }
    )
    @DeleteMapping(value = "/{taxi-park-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    void deleteTaxiPark(@ApiParam(value = "ID таксопарка", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                        @PathVariable("taxi-park-id") UUID taxiParkId);


    @ApiOperation(value = "Обновить таксопарк по его ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Таксопарк успешно обновлен", response = TaxiParkResponse.class),
            @ApiResponse(code = 400, message = "Bad Request. Запрос содержит невалидные данные", response = ExceptionExtendedResponse.class),
            @ApiResponse(code = 404, message = "Not Found. Таксопарк с таким ID не найден", response = ExceptionResponse.class),
            @ApiResponse(code = 409, message = "Conflict. Таксопарк с таким названием уже существует", response = ExceptionResponse.class)
    }
    )
    @PutMapping(value = "/{taxi-park-id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    TaxiParkResponse updateTaxiPark(@ApiParam(value = "ID таксопарка", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                                    @PathVariable("taxi-park-id") UUID taxiParkId, @ApiParam(value = "Обновленные значения таксопарка")
                                    @Valid @RequestBody TaxiParkRequest taxiPark);
}
