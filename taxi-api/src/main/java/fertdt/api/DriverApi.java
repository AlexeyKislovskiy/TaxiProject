package fertdt.api;

import fertdt.dto.response.DriverResponse;
import fertdt.dto.response.PassportResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/drivers")
public interface DriverApi {
    @PostMapping(value = "/{user-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    void createDriverAccount(@PathVariable("user-id") UUID userId);

    @GetMapping(value = "/{driver-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    DriverResponse getDriver(@PathVariable("driver-id") UUID driverId);
}
