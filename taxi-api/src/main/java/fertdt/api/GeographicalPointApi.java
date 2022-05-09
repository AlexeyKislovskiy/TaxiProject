package fertdt.api;

import fertdt.dto.response.GeographicalPointResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RequestMapping("/api/geographical-points")
public interface GeographicalPointApi {
    @GetMapping(value = "/{geographical-point-id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    GeographicalPointResponse getGeographicalPoint(@PathVariable("geographical-point-id") UUID geographicalPointId);
}
