package fertdt.api;

import fertdt.dto.request.TaxiCallRequest;
import fertdt.dto.response.TaxiRideResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/taxi-call")
public interface TaxiRideApi {

    @PostMapping(value = "/call", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    UUID callTaxi(@Valid @RequestBody TaxiCallRequest taxiCallRequest);

    @DeleteMapping(value = "call/{user-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void cancelTaxiCall(@PathVariable("user-id") UUID userId);

    @GetMapping(value = "/{taxi-ride-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    TaxiRideResponse getTaxiRide(@PathVariable("taxi-ride-id") UUID taxiRideId);

    @PostMapping(value = "/take/{driver-id}/{taxi-ride-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void takeOrder(@PathVariable("driver-id") UUID driverId, @PathVariable("taxi-ride-id") UUID taxiRideId);

    @PostMapping("/arrive-to-client/{driver-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void arriveToClient(@PathVariable("driver-id") UUID driverId);

    @PostMapping("/start/{driver-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void startTrip(@PathVariable("driver-id") UUID driverId);

    @DeleteMapping(value = "/{driver-id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void cancelTrip(@PathVariable("driver-id") UUID driverId);
}
