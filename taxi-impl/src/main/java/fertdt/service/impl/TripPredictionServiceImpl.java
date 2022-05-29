package fertdt.service.impl;

import fertdt.dto.PredictedTripDto;
import fertdt.dto.request.GeographicalCoordinatesRequest;
import fertdt.dto.request.UpcomingTaxiCallRequest;
import fertdt.dto.response.CarClassResponse;
import fertdt.dto.response.GeographicalPointResponse;
import fertdt.exception.MapboxApiException;
import fertdt.service.CarClassService;
import fertdt.service.NavigationService;
import fertdt.service.TripPredictionService;
import fertdt.util.mapper.TripPredictionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static fertdt.consts.Constants.*;

@Service
@RequiredArgsConstructor
public class TripPredictionServiceImpl implements TripPredictionService {
    private final RestTemplate restTemplate;
    private final TripPredictionMapper tripPredictionMapper;
    private final CarClassService carClassService;
    private final NavigationService navigationService;

    @Value("${mapbox.access-token}")
    private String mapboxAccessToken;

    @Override
    public PredictedTripDto predictTrip(UpcomingTaxiCallRequest taxiCallRequest) {
        CarClassResponse carClass = carClassService.getClassCarById(taxiCallRequest.getCarClassId());
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put(ACCESS_TOKEN_PARAMETER, mapboxAccessToken);
        List<GeographicalCoordinatesRequest> coordinates = new ArrayList<>();
        coordinates.add(taxiCallRequest.getStartingPoint());
        if (taxiCallRequest.getIntermediatePoints() != null)
            coordinates.addAll(taxiCallRequest.getIntermediatePoints());
        coordinates.add(taxiCallRequest.getDestination());
        uriVariables.put(COORDINATES_PARAMETER, listOfCoordinatesToString(coordinates));
        ResponseEntity<String> response;
        PredictedTripDto predictedTripDto;
        if (taxiCallRequest.getIntermediatePointsOrderImportant() == null ||
                taxiCallRequest.getIntermediatePointsOrderImportant()) {
            response = restTemplate.getForEntity(ORDERED_TRIP_PREDICTION_URI, String.class, uriVariables);
            if (response.getStatusCode().equals(HttpStatus.OK))
                predictedTripDto = tripPredictionMapper.toResponseFromOrdered(response.getBody(), carClass);
            else
                throw new MapboxApiException(response.getStatusCode(), "Problem with Mapbox API, cannot do trip prediction");
        } else {
            response = restTemplate.getForEntity(OPTIMIZED_TRIP_PREDICTION_URI, String.class, uriVariables);
            if (response.getStatusCode().equals(HttpStatus.OK))
                predictedTripDto = tripPredictionMapper.toResponseFromOptimized(response.getBody(), carClass);
            else
                throw new MapboxApiException(response.getStatusCode(), "Problem with Mapbox API, cannot do trip prediction");
        }
        predictedTripDto.setPredictedDriverWaitingTime(getPredictedDriverWaitingTime(taxiCallRequest));
        return predictedTripDto;
    }


    private String listOfCoordinatesToString(List<GeographicalCoordinatesRequest> geographicalCoordinatesList) {
        StringBuilder stringOfCoordinates = new StringBuilder();
        for (GeographicalCoordinatesRequest geographicalCoordinates : geographicalCoordinatesList) {
            stringOfCoordinates.append(geographicalCoordinates.getLongitude().toString().concat(",").concat(geographicalCoordinates.getLatitude().toString()));
            if (!geographicalCoordinates.equals(geographicalCoordinatesList.get(geographicalCoordinatesList.size() - 1)))
                stringOfCoordinates.append(";");
        }
        return stringOfCoordinates.toString();
    }

    private Integer getPredictedDriverWaitingTime(UpcomingTaxiCallRequest taxiCallRequest) {
        GeographicalPointResponse driverLocation = navigationService.findLocationOfNearestDriver(taxiCallRequest);
        if (driverLocation == null) return null;
        Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put(ACCESS_TOKEN_PARAMETER, mapboxAccessToken);
        List<GeographicalCoordinatesRequest> coordinates = new ArrayList<>();
        coordinates.add(taxiCallRequest.getStartingPoint());
        coordinates.add(GeographicalCoordinatesRequest.builder()
                .longitude(driverLocation.getLongitude())
                .latitude(driverLocation.getLatitude())
                .build());
        uriVariables.put(COORDINATES_PARAMETER, listOfCoordinatesToString(coordinates));
        ResponseEntity<String> response = restTemplate.getForEntity(ORDERED_TRIP_PREDICTION_URI, String.class, uriVariables);
        if (response.getStatusCode().equals(HttpStatus.OK))
            return tripPredictionMapper.toResponseFromOrdered(response.getBody(), null).getPredictedTripTime();
        else
            throw new MapboxApiException(response.getStatusCode(), "Problem with Mapbox API, cannot do trip prediction");
    }
}
