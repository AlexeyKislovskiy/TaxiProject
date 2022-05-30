package fertdt.api;

import fertdt.dto.PredictedTripDto;
import fertdt.dto.request.UpcomingTaxiCallRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/trip-prediction")
public interface TripPredictionApi {

    @PostMapping(produces = APPLICATION_JSON_VALUE, consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    PredictedTripDto predictTrip(@Valid @RequestBody UpcomingTaxiCallRequest taxiCallRequest);

    @GetMapping("{user-id}")
    @ResponseStatus(HttpStatus.OK)
    Integer predictTimeToDriverArriving(@PathVariable("user-id") UUID userId);
}
