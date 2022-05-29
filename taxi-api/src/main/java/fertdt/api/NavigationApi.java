package fertdt.api;

import fertdt.dto.request.NearestDriversRequest;
import fertdt.dto.response.DriverResponse;
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

    @PostMapping(value = "/nearest-drivers", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    Set<DriverResponse> findNearestDrivers(@Valid @RequestBody NearestDriversRequest nearestDriversRequest);
}
