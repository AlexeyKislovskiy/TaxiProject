package fertdt.api;

import fertdt.dto.response.DriverResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/drivers")
public interface DriverApi {

    @PostMapping(value = "/{user-id}")
    @ResponseStatus(HttpStatus.CREATED)
    void createDriverAccount(@PathVariable("user-id") UUID userId);

    @GetMapping(value = "/{driver-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    DriverResponse getDriver(@PathVariable("driver-id") UUID driverId);

    @PostMapping(value = "/verify/{driver-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void verifyDriverAccount(@PathVariable("driver-id") UUID driverId);

    @PostMapping(value = "/contract/{driver-id}/{taxi-park-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void signContractWithTaxiPark(@PathVariable("driver-id") UUID driverId, @PathVariable("taxi-park-id") UUID taxiParkId);

    @DeleteMapping(value = "/contract/{driver-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void breakContractWithTaxiPark(@PathVariable("driver-id") UUID driverId);
}
