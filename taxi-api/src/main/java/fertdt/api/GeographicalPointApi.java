package fertdt.api;

import fertdt.dto.response.ExceptionResponse;
import fertdt.dto.response.GeographicalPointResponse;
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

@RequestMapping("/api/geographical-points")
public interface GeographicalPointApi {
    @ApiOperation(value = "Получить информацию о географической точке по ее ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK. Географическая точка успешно получена", response = GeographicalPointResponse.class),
            @ApiResponse(code = 404, message = "Not Found. Географическая точка с таким ID не найдена", response = ExceptionResponse.class)
    }
    )
    @GetMapping(value = "/{geographical-point-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    GeographicalPointResponse getGeographicalPoint(@ApiParam(value = "ID географической точки", example = "3fa85f64-5717-4562-b3fc-2c963f66afa6")
                                                   @PathVariable("geographical-point-id") UUID geographicalPointId);
}
