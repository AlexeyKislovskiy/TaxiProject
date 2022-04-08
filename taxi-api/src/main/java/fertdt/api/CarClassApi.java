package fertdt.api;

import fertdt.dto.response.CarClassResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/carClasses")
public interface CarClassApi {
    @GetMapping(value = "/{carClass-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    CarClassResponse getCarClass(@PathVariable("carClass-id") UUID carClassId);
}
