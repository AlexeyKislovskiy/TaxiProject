package fertdt.service;

import fertdt.dto.request.GeographicalCoordinatesRequest;
import fertdt.dto.response.GeographicalPointResponse;
import fertdt.model.GeographicalPointEntity;

import java.util.List;
import java.util.UUID;

public interface GeographicalPointService {
    GeographicalPointEntity getGeographicalPointFromCoordinates(GeographicalCoordinatesRequest geographicalCoordinatesRequest);

    GeographicalPointResponse getGeographicalPoint(UUID geographicalPointId);

    boolean correctTaxiCallGeographicalPoints(List<GeographicalPointEntity> geographicalPoints);
}
