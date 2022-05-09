package fertdt.controller;

import fertdt.api.GeocodingApi;
import fertdt.dto.request.ForwardGeocodingRequest;
import fertdt.dto.request.ReverseGeocodingRequest;
import fertdt.dto.response.GeographicalPointResponse;
import fertdt.service.GeocodingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GeocodingController implements GeocodingApi {
    private final GeocodingService geocodingService;

    @Override
    public List<GeographicalPointResponse> forwardGeocoding(ForwardGeocodingRequest forwardGeocodingRequest) {
        return geocodingService.forwardGeocoding(forwardGeocodingRequest);
    }

    @Override
    public List<GeographicalPointResponse> reverseGeocoding(ReverseGeocodingRequest reverseGeocodingRequest) {
        return geocodingService.reverseGeocoding(reverseGeocodingRequest);
    }
}
