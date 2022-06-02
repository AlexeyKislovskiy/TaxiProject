package fertdt.api;

import fertdt.annotation.GlobalApiResponses;
import fertdt.dto.response.DriverResponse;
import fertdt.dto.response.ExceptionResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/drivers")
@GlobalApiResponses
public interface DriverApi<PRINCIPAL> {
    @ApiOperation(value = "Создать аккаунт водителя", code = 201)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created. Аккаунт успешно создан"),
            @ApiResponse(code = 409, message = "Conflict. У данного пользователя уже есть аккаунт водителя",
                    response = ExceptionResponse.class)
    }
    )
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    void createDriverAccount(@ApiIgnore @AuthenticationPrincipal PRINCIPAL user);


    @ApiOperation(value = "Получить информацию о водителе по его ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Водитель успешно получен", response = DriverResponse.class),
            @ApiResponse(code = 404, message = "Not Found. Водитель с таким ID не найден", response = ExceptionResponse.class)
    }
    )
    @GetMapping(value = "/{driver-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    DriverResponse getDriver(@ApiParam(value = "ID водителя", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                             @PathVariable("driver-id") UUID driverId);


    @ApiOperation(value = "Верифицировать аккаунт водителя", code = 202)
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted. Аккаунт успешно верифицирован"),
            @ApiResponse(code = 404, message = "Not Found. Водитель с таким ID не найден", response = ExceptionResponse.class),
            @ApiResponse(code = 406, message = "Not Acceptable. Нет необходимых документов для верификации", response = ExceptionResponse.class),
            @ApiResponse(code = 409, message = "Conflict. Аккаунт данного водителя уже верифицирован",
                    response = ExceptionResponse.class)
    }
    )
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCUMENTS_MODERATOR')")
    @PostMapping(value = "/verify/{driver-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void verifyDriverAccount(@ApiParam(value = "ID водителя", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                             @PathVariable("driver-id") UUID driverId);


    @ApiOperation(value = "Заключить контракт с таксопарком", code = 202)
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted. Контракт успешно заключен"),
            @ApiResponse(code = 404, message = "Not Found. Возможные проблемы: водитель с таким ID не найден, " +
                    "таксопарк с таким ID не найден", response = ExceptionResponse.class),
            @ApiResponse(code = 406, message = "Not Acceptable. Аккаунт водителя не верифицирован", response = ExceptionResponse.class),
            @ApiResponse(code = 409, message = "Conflict. Данный водитель уже заключил контракт с таксопарком",
                    response = ExceptionResponse.class)
    }
    )
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_TAXI_PARK_MODERATOR')")
    @PostMapping(value = "work/contract/{driver-id}/{taxi-park-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void signContractWithTaxiPark(@ApiParam(value = "ID водителя", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                                  @PathVariable("driver-id") UUID driverId,
                                  @ApiParam(value = "ID таксопарка", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                                  @PathVariable("taxi-park-id") UUID taxiParkId);


    @ApiOperation(value = "Расторгнуть контракт с таксопарком", code = 202)
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted. Контракт успешно расторгнут"),
            @ApiResponse(code = 404, message = "Not Found. Водитель с таким ID не найден", response = ExceptionResponse.class),
            @ApiResponse(code = 406, message = "Not Acceptable. У данного водителя и так нет контракта", response = ExceptionResponse.class),
    }
    )
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_TAXI_PARK_MODERATOR')")
    @DeleteMapping(value = "work/contract/{driver-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void breakContractWithTaxiPark(@ApiParam(value = "ID водителя", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                                   @PathVariable("driver-id") UUID driverId);


    @ApiOperation(value = "Выйти на работу", code = 202)
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted. Водитель успешно начал работу"),
            @ApiResponse(code = 406, message = "Not Acceptable. Возможные проблемы: водитель уже на работе," +
                    " водитель не использует никакую машину, аккаунт водителя не верифицирован, у водителя нет контракта " +
                    "с таксопарком", response = ExceptionResponse.class),
            @ApiResponse(code = 409, message = "Conflict. У водителя активна поездка в качестве пассажира",
                    response = ExceptionResponse.class)
    }
    )
    @PostMapping(value = "work/start", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void startWork(@ApiIgnore @AuthenticationPrincipal PRINCIPAL driver);


    @ApiOperation(value = "Закончить работу", code = 202)
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted. Водитель успешно закончил работу"),
            @ApiResponse(code = 406, message = "Not Acceptable. Возможные проблемы: водитель и так не на работе," +
                    " у водителя есть незаконченная поездка, аккаунт водителя не верифицирован," +
                    " у водителя нет контракта с таксопарком", response = ExceptionResponse.class)
    }
    )
    @DeleteMapping(value = "work/stop", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    void stopWork(@ApiIgnore @AuthenticationPrincipal PRINCIPAL driver);
}
