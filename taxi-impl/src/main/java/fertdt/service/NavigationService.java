package fertdt.service;

import fertdt.dto.request.NearestDriversRequest;
import fertdt.dto.request.UpcomingTaxiCallRequest;
import fertdt.dto.response.DriverResponse;
import fertdt.dto.response.GeographicalPointResponse;

import java.util.Set;

public interface NavigationService {
    Set<DriverResponse> findNearestDrivers(NearestDriversRequest nearestDriversRequest);

    GeographicalPointResponse findLocationOfNearestDriver(UpcomingTaxiCallRequest taxiCallRequest);
}
