package fertdt.service;

import fertdt.dto.request.NearestDriversRequest;
import fertdt.dto.response.DriverResponse;

import java.util.Set;

public interface NavigationService {
    Set<DriverResponse> findNearestDrivers(NearestDriversRequest nearestDriversRequest);
}
