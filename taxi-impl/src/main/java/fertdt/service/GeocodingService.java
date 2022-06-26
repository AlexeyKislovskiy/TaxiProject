package fertdt.service;

import fertdt.dto.request.ForwardGeocodingRequest;
import fertdt.dto.request.ReverseGeocodingRequest;
import fertdt.dto.response.GeographicalPointResponse;

import java.util.List;

public interface GeocodingService {
    List<GeographicalPointResponse> forwardGeocoding(ForwardGeocodingRequest forwardGeocodingRequest);

    List<GeographicalPointResponse> reverseGeocoding(ReverseGeocodingRequest reverseGeocodingRequest);
}
