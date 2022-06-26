package fertdt.api;

import fertdt.annotation.GlobalApiResponses;
import fertdt.dto.request.DriverLicenseRequest;
import fertdt.dto.response.DriverLicenseResponse;
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

@RequestMapping("/api/driver-licenses")
@GlobalApiResponses
public interface DriverLicenseApi {
    @ApiOperation(value = "Добавить водительские права", code = 201)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created. Водительские права успешно добавлены", response = UUID.class),
            @ApiResponse(code = 400, message = "Bad Request. Запрос содержит невалидные данные", response = ExceptionExtendedResponse.class),
            @ApiResponse(code = 404, message = "Not Found. Возможные проблемы: водитель с таким ID не найден," +
                    " категория транспортного средства с таким ID не найдена", response = ExceptionResponse.class),
            @ApiResponse(code = 409, message = "Conflict. Водительские права с таким номером уже существуют", response = ExceptionResponse.class)
    }
    )
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCUMENTS_MODERATOR')")
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    UUID addDriverLicense(@ApiParam(value = "Водительские права") @Valid @RequestBody DriverLicenseRequest driverLicense);


    @ApiOperation(value = "Получить информацию о водительских правах по их ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Водительские права успешно получены", response = DriverLicenseResponse.class),
            @ApiResponse(code = 404, message = "Not Found. Водительские права с таким ID не найдены", response = ExceptionResponse.class)
    }
    )
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCUMENTS_MODERATOR')")
    @GetMapping(value = "/{driver-license-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    DriverLicenseResponse getDriverLicense(@ApiParam(value = "ID водительских прав", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                                           @PathVariable("driver-license-id") UUID driverLicenseId);


    @ApiOperation(value = "Удалить водительские права по их ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Водительские права успешно удалены"),
            @ApiResponse(code = 404, message = "Not Found. Водительские права с таким ID не найдены", response = ExceptionResponse.class)
    }
    )
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_DOCUMENTS_MODERATOR')")
    @DeleteMapping(value = "/{driver-license-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    void deleteDriverLicense(@ApiParam(value = "ID водительских прав", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                             @PathVariable("driver-license-id") UUID driverLicenseId);
}
