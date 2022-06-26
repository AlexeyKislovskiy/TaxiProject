package fertdt.service.impl;

import fertdt.dto.request.ForwardGeocodingRequest;
import fertdt.dto.request.ReverseGeocodingRequest;
import fertdt.dto.response.GeographicalPointResponse;
import fertdt.exception.MapboxApiException;
import fertdt.service.GeocodingService;
import fertdt.util.mapper.GeocodingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static fertdt.consts.Constants.*;

@Service
@RequiredArgsConstructor
public class GeocodingServiceImpl implements GeocodingService {
    private final RestTemplate restTemplate;
    private final GeocodingMapper geocodingMapper;

    @Value("${mapbox.access-token}")
    private String mapboxAccessToken;

    @Override
    public List<GeographicalPointResponse> forwardGeocoding(ForwardGeocodingRequest forwardGeocodingRequest) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put(NAME_PARAMETER, forwardGeocodingRequest.getName());
        uriVariables.put(LIMIT_PARAMETER, forwardGeocodingRequest.getLimit().toString());
        uriVariables.put(ACCESS_TOKEN_PARAMETER, mapboxAccessToken);
        String uri;
        if (forwardGeocodingRequest.getProximityCoordinates() != null) {
            uriVariables.put(PROXIMITY_PARAMETER, forwardGeocodingRequest.getProximityCoordinates().getLongitude().toString()
                    .concat(",").concat(forwardGeocodingRequest.getProximityCoordinates().getLatitude().toString()));
            uri = FORWARD_GEOCODING_URI_WITH_PROXIMITY;
        } else uri = FORWARD_GEOCODING_URI;
        ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class, uriVariables);
        if (response.getStatusCode().equals(HttpStatus.OK)) return geocodingMapper.toResponse(response.getBody());
        else throw new MapboxApiException(response.getStatusCode(), "Problem with Mapbox API, cannot do forwarding geocoding");
    }

    @Override
    public List<GeographicalPointResponse> reverseGeocoding(ReverseGeocodingRequest reverseGeocodingRequest) {
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put(LONGITUDE_PARAMETER, reverseGeocodingRequest.getGeographicalCoordinates().getLongitude().toString());
        uriVariables.put(LATITUDE_PARAMETER, reverseGeocodingRequest.getGeographicalCoordinates().getLatitude().toString());
        uriVariables.put(ACCESS_TOKEN_PARAMETER, mapboxAccessToken);
        ResponseEntity<String> response = restTemplate.getForEntity(REVERSE_GEOCODING_URI, String.class, uriVariables);
        if (response.getStatusCode().equals(HttpStatus.OK)) return geocodingMapper.toResponse(response.getBody());
        else throw new MapboxApiException(response.getStatusCode(), "Problem with Mapbox API, cannot do reverse geocoding");
    }
}
