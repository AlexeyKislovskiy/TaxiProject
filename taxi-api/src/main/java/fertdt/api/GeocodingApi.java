package fertdt.api;

import fertdt.dto.request.ForwardGeocodingRequest;
import fertdt.dto.request.ReverseGeocodingRequest;
import fertdt.dto.response.GeographicalPointResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/geocoding")
public interface GeocodingApi {
    @PostMapping(value = "/forward", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    List<GeographicalPointResponse> forwardGeocoding(@Valid @RequestBody ForwardGeocodingRequest forwardGeocodingRequest);

    @PostMapping(value = "/reverse", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    List<GeographicalPointResponse> reverseGeocoding(@Valid @RequestBody ReverseGeocodingRequest reverseGeocodingRequest);
}
