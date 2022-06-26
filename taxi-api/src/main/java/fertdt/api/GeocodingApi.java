package fertdt.api;

import fertdt.annotation.GlobalApiResponses;
import fertdt.dto.request.ForwardGeocodingRequest;
import fertdt.dto.request.ReverseGeocodingRequest;
import fertdt.dto.response.ExceptionExtendedResponse;
import fertdt.dto.response.GeographicalPointResponse;
import fertdt.dto.response.UserResponse;
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
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/geocoding")
@GlobalApiResponses
public interface GeocodingApi {
    @ApiOperation(value = "Прямое геокодирование(из названия места в его географические координаты)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Список географических точек успешно получен", response = UserResponse.class),
            @ApiResponse(code = 400, message = "Bad Request. Запрос содержит невалидные данные", response = ExceptionExtendedResponse.class)
    }
    )
    @PostMapping(value = "/forward", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    List<GeographicalPointResponse> forwardGeocoding(@ApiParam(value = "Запрос на прямое геокодирование") @Valid @RequestBody ForwardGeocodingRequest forwardGeocodingRequest);


    @ApiOperation(value = "Обратное геокодирование(из географических координат места в его название)")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Список географических точек успешно получен", response = UserResponse.class),
            @ApiResponse(code = 400, message = "Bad Request. Запрос содержит невалидные данные", response = ExceptionExtendedResponse.class)
    }
    )
    @PostMapping(value = "/reverse", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    List<GeographicalPointResponse> reverseGeocoding(@ApiParam(value = "Запрос на обратное геокодирование") @Valid @RequestBody ReverseGeocodingRequest reverseGeocodingRequest);
}
