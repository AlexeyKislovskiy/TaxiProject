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

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    UUID callTaxi(@Valid @RequestBody TaxiCallRequest taxiCallRequest);

    @DeleteMapping(value = "/{user-id}")
    @ResponseStatus(HttpStatus.OK)
    void cancelTaxiCall(@PathVariable("user-id") UUID userId);

    @GetMapping(value = "/{taxi-ride-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    TaxiRideResponse getTaxiRide(@PathVariable("taxi-ride-id") UUID taxiRideId);
}
