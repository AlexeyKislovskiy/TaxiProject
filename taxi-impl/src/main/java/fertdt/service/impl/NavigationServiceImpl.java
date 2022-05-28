package fertdt.service.impl;

import fertdt.dto.request.NearestDriversRequest;
import fertdt.dto.response.DriverResponse;
import fertdt.model.TaxiRideEntity;
import fertdt.service.NavigationService;
import fertdt.service.TaxiRideService;
import fertdt.util.NavigationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NavigationServiceImpl implements NavigationService {
    private final TaxiRideService taxiRideService;

    @Override
    public Set<DriverResponse> findNearestDrivers(NearestDriversRequest nearestDriversRequest) {
        TaxiRideEntity taxiRide = taxiRideService.getTaxiRideEntityById(nearestDriversRequest.getTaxiRideId());
        return taxiRideService.findAllFreeDrivers(taxiRide).stream()
                .sorted(Comparator.comparingDouble(a -> NavigationUtil.distance(a.getCurrentLocation().getLatitude(),
                        a.getCurrentLocation().getLongitude(), taxiRide.getStartingPoint().getLatitude(),
                        taxiRide.getStartingPoint().getLongitude())))
                .limit(nearestDriversRequest.getLimit()).collect(Collectors.toSet());
    }
}
