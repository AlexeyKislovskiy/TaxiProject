package fertdt.api;

import fertdt.dto.request.TaxiParkRequest;
import fertdt.dto.response.TaxiParkResponse;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/taxi-parks")
public interface TaxiParkApi {
    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    UUID createTaxiPark(@Valid @RequestBody TaxiParkRequest taxiPark);

    @GetMapping(value = "/{taxi-park-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    TaxiParkResponse getTaxiPark(@PathVariable("taxi-park-id") UUID taxiParkId);

    @DeleteMapping(value = "/{taxi-park-id}")
    @ResponseStatus(HttpStatus.OK)
    void deleteTaxiPark(@PathVariable("taxi-park-id") UUID taxiParkId);

    @PutMapping(value = "/{taxi-park-id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    TaxiParkResponse updateTaxiPark(@PathVariable("taxi-park-id") UUID taxiParkId, @Valid @RequestBody TaxiParkRequest taxiPark);
}
