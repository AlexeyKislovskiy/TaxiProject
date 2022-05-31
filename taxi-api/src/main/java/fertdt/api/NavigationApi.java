package fertdt.api;

import fertdt.dto.request.NearestDriversRequest;
import fertdt.dto.response.DriverResponse;
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
import java.util.Set;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/navigation")
public interface NavigationApi {
    @ApiOperation(value = "Найти ближайших водителей, подходящих для заданного заказа")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Список водителей успешно получен"),
            @ApiResponse(code = 400, message = "Bad Request. Запрос содержит невалидные данные", response = ExceptionExtendedResponse.class),
            @ApiResponse(code = 404, message = "Not Found. Поездка с таким ID не найдена", response = ExceptionResponse.class)
    }
    )
    @PostMapping(value = "/nearest-drivers", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    Set<DriverResponse> findNearestDrivers(@ApiParam(value = "Запрос на поиск ближайших водителей")
                                           @Valid @RequestBody NearestDriversRequest nearestDriversRequest);
}
