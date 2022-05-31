package fertdt.api;

import fertdt.dto.response.CarClassResponse;
import fertdt.dto.response.ExceptionResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/car-classes")
public interface CarClassApi {
    @ApiOperation(value = "Получить информацию о классе автомобиля по его ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Класс автомобиля успешно получен", response = CarClassResponse.class),
            @ApiResponse(code = 404, message = "Not Found. Класс автомобиля с таким ID не найден", response = ExceptionResponse.class)
    }
    )
    @GetMapping(value = "/{car-class-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    CarClassResponse getCarClass(@ApiParam(value = "ID класса автомобиля", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                                 @PathVariable("car-class-id") UUID carClassId);
}
